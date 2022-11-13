import '../assets/css/UserManagementPage.css'
import '../assets/css/CourseManagement.css'
import 'jsoneditor-react/es/editor.min.css'
import { JsonEditor as Editor } from 'jsoneditor-react'
import { Header, Footer, Sider, Pagination, ModalConfirm } from "../components"
import { ROUTE_PATH, STATUS_CODES } from '../constants'
import { Button, Card, Badge, OverlayTrigger, Tooltip, Offcanvas } from 'react-bootstrap'
import { SearchBox, UserInfo } from '../components'
import { useSelector } from 'react-redux'
import { QuestionApi } from '../api'
import { useQuery } from '@tanstack/react-query'
import { useParams } from 'react-router'
import { useLayoutEffect, useCallback, useState } from 'react'
import { useDispatch } from "react-redux"
import { showLoader, hideLoader, showNotFound, hideNotFound } from '../redux/actions'
import _ from 'lodash'
import { Notification } from '../utils'
import uuid from 'react-uuid'

const baseQuestionCreate = () => ({
    groupId: uuid(),
    questions: [
        {
            id: uuid(),
            questionType: "VOCABULARY",
            header: "Đây là câu hỏi kiểu: đọc đoạn văn và trả lời câu hỏi (Cái này là paste đoạn text câu hỏi y hệt trong đề thi toiec của mỗi phần)",
            text: "Đoạn văn của câu hỏi",
            image: "https://firebasestorage.googleapis.com/v0/b/learne-41d47.appspot.com/o/Backend-Service-Multimedia%2FQuestionBank%2FQuestion%2Fd085dae7-7afc-47af-8162-0bf1ec9a5cda%2F143c9125-ca6a-4ec4-8e44-9cdfa9711b62.jpg?alt=media",
            audio: "https://firebasestorage.googleapis.com/v0/b/learne-41d47.appspot.com/o/Backend-Service-Multimedia%2FQuestionBank%2FQuestion%2Fd085dae7-7afc-47af-8162-0bf1ec9a5cda%2F143c9125-ca6a-4ec4-8e44-9cdfa9711b62.mp3?alt=media",
            answers: [
                {
                    text: "Đáp án 1",
                    is_correct: true
                },
                {
                    text: "Đáp án 2",
                    is_correct: false
                },
                {
                    text: "Đáp án 3",
                    is_correct: false
                },
                {
                    text: "Đáp án 4",
                    is_correct: false
                }
            ]
        }
    ]
})

const QuestionManagement = (props) => {

    const dispatch = useDispatch()
    const { page } = useParams()
    const user = useSelector(state => state.user)
    const size = 12
    const [showCVEQuestion, setShowCVEQuestion] = useState({ show: false })
    const [questionRemove, setQuestionRemove] = useState(false)
    const [questionCreateUpdate, setQuestionCreateUpdate] = useState(false)

    const { data: responseGetAllQuestions, isLoading: isLoadingGetAllQuestions, isFetching: isFetchingGetAllQuestions, isError: isErrorGetAllQuestions, refetch: getAllQuestions } = useQuery(
        ["getAllQuestions", page],
        () => QuestionApi.getAll(page, size)
            // .then(response => {
            //     const { meta } = response
            //     if (meta.code === STATUS_CODES.SUCCESS) {
            //         const { data } = response
            //         const { content } = data

            //         content.groups = [...content
            //             .reduce((map, question) => {
            //                 map.set(question.groupId, question)
            //                 return map
            //             }, new Map())]
            //             .map(([key, value]) => ({
            //                 groupId: key,
            //                 questions: value
            //             }))
            //     }
            //     return response
            // })
        ,{
            refetchOnWindowFocus: false,
        }
    )

    useLayoutEffect(() => {
        if (isLoadingGetAllQuestions || isFetchingGetAllQuestions) {
            dispatch(showLoader())
        } else {
            dispatch(hideLoader())
        }

        if (isErrorGetAllQuestions || (responseGetAllQuestions?.meta && responseGetAllQuestions?.meta?.code !== STATUS_CODES.SUCCESS)) {
            dispatch(showNotFound())
        } else {
            dispatch(hideNotFound())
        }

        return () => {
            dispatch(hideLoader())
            dispatch(hideNotFound())
        }
    }, [responseGetAllQuestions, dispatch, isErrorGetAllQuestions, isFetchingGetAllQuestions, isLoadingGetAllQuestions])

    const refreshPage = useCallback(() => {
        getAllQuestions(page)
    }, [ getAllQuestions, page ])

    useLayoutEffect(() => {
        if (showCVEQuestion?.show === true && showCVEQuestion?.type !== 'create' && showCVEQuestion?.newData !== true) {
            dispatch(showLoader())
            QuestionApi.getByGroupId(showCVEQuestion?.data?.groupId)
                .then(res => {
                    const { meta } = res
                    if (meta.code === STATUS_CODES.SUCCESS) {
                        const { data } = res
                        const oldGroupId = data.groupId
                        if(showCVEQuestion?.type === 'update') {
                            data.groupId = uuid()
                            data.questions.forEach(question => question.id = uuid())
                        }
                        setShowCVEQuestion(previousShowCVEQuestion => ({ ...previousShowCVEQuestion, data: {...data}, newData: true, oldGroupId }))
                        dispatch(hideLoader())
                    } else {
                        setShowCVEQuestion({ show: false })
                        dispatch(hideLoader())
                    }
                })
        }

    }, [showCVEQuestion, dispatch])

    const handleCreateUpdatCoursae = () => {
        dispatch(showLoader())

        if(showCVEQuestion?.type === 'create') {
            QuestionApi.createList(questionCreateUpdate)
            .then(res => {
                const { meta } = res
                if(meta.code === STATUS_CODES.SUCCESS) {
                    refreshPage()
                    setShowCVEQuestion({ show: false })
                    setQuestionCreateUpdate(false)
                    Notification.success("Create successfully!")
                } else {
                    dispatch(hideLoader())
                    Notification.error(meta?.message)
                }
            })            
        } else if(showCVEQuestion?.type === 'update') {
            // Thực hiện tạo cái mới trước
            QuestionApi.createList(questionCreateUpdate)
            .then(res => {
                const { meta } = res
                if(meta.code === STATUS_CODES.SUCCESS) {
                    // Tạo thành công sẽ xóa cái cũ
                    QuestionApi.deleteByGroupId(showCVEQuestion?.oldGroupId)
                    .then(res => {
                        const { meta } = res
                        if (meta.code === STATUS_CODES.SUCCESS) {
                            refreshPage()
                            setShowCVEQuestion({ show: false })
                            setQuestionCreateUpdate(false)
                            Notification.success("Update successfully!")
                        } else {
                            dispatch(hideLoader())
                            Notification.error(meta?.message)
                        }
                    })
                } else {
                    dispatch(hideLoader())
                    Notification.error(meta?.message)
                }
            })       
        }
    }

    const handleDeleteQuestionGroup = () => {
        dispatch(showLoader())
        QuestionApi.deleteByGroupId(questionRemove?.groupId)
            .then(res => {
                const { meta } = res
                if (meta.code === STATUS_CODES.SUCCESS) {
                    refreshPage()
                    setQuestionRemove(false)
                    Notification.success("Remove successfully!")
                } else {
                    dispatch(hideLoader())
                    Notification.error(meta?.message)
                }
            })
    }

    return !isLoadingGetAllQuestions && !isFetchingGetAllQuestions && !isErrorGetAllQuestions && responseGetAllQuestions?.meta?.code === STATUS_CODES.SUCCESS && !_.isEmpty(responseGetAllQuestions?.data) && <>
        <Header />
        <Sider>
            <div className="UserManagementPage-container">
                {/* Phần các chức năng bên trên */}
                <div className="func">
                    <div className="container-xl">
                        <div className="row">
                            <div className="col-md">
                                <UserInfo limit={30} user={user} />
                            </div>
                            <div className="col-md d-flex align-items-end justify-content-between mt-4 mt-md-0">
                                <SearchBox placeholder="Search" />
                                <Button
                                    className='h-fit font-bold'
                                    onClick={() => setShowCVEQuestion({
                                        type: 'create',
                                        show: true,
                                        data: baseQuestionCreate()
                                    })}
                                >Create</Button>
                            </div>
                        </div>
                    </div>
                </div>

                <div className="content pb-3">
                    <div className="container-xl">
                        <Pagination className="row"
                            classNamePagination={`m-0 mt-3`}
                            hrefPrev={`${ROUTE_PATH.ADMIN_QUESTION_VIEW_ALL}/${parseInt(page) - 1}`}
                            hrefNext={`${ROUTE_PATH.ADMIN_QUESTION_VIEW_ALL}/${parseInt(page) + 1}`}
                            hrefCurrent={`${ROUTE_PATH.ADMIN_QUESTION_VIEW_ALL}/${parseInt(page)}`}
                            disabledPrev={responseGetAllQuestions?.data?.first}
                            disabledNext={responseGetAllQuestions?.data?.last}
                            onClickCurrent={refreshPage}
                            page={parseInt(page) + 1}
                            totalPages={responseGetAllQuestions?.data?.totalPages}
                            hide={(responseGetAllQuestions?.data && (responseGetAllQuestions?.data?.totalElements <= size || _.isEmpty(responseGetAllQuestions?.data?.content))) ? true : false}
                        >
                            {
                                responseGetAllQuestions?.data?.content?.map((question, indexQuestion) => <div key={question?.id} className="col-sm-6 col-md-4 col-lg-3 mt-3">
                                    <Card className="card-user cursor-default">
                                        {/* <Card.Img className='cursor-pointer border-b' variant="top" src={question?.image || PagesImage} onClick={() => setShowCVEQuestion({ type: 'view', data: _.cloneDeep(question), show: true })} /> */}
                                        <Card.Body className='cursor-pointer' onClick={() => setShowCVEQuestion({ type: 'view', data: _.cloneDeep(question), show: true })}>
                                            <Card.Title className="title text-lg">Question {responseGetAllQuestions?.data?.pageable?.offset + indexQuestion + 1}</Card.Title>
                                            <Card.Subtitle className="mb-2 text-muted text-sm">
                                                <Badge pill bg="warning" text="dark">{question?.questionType}</Badge>
                                            </Card.Subtitle>
                                        </Card.Body>

                                        <Card.Footer className="d-flex justify-content-between">
                                            <OverlayTrigger placement="bottom" overlay={<Tooltip>Edit</Tooltip>}>
                                                <Badge bg="primary" className='cursor-pointer' onClick={() => { setShowCVEQuestion({ data: _.cloneDeep(question), show: true, type: 'update' }) }}>
                                                    <i className="fas fa-edit fs-6" />
                                                </Badge>
                                            </OverlayTrigger>

                                            <OverlayTrigger placement="bottom" overlay={<Tooltip>Remove</Tooltip>}>
                                                <Badge bg="danger" className='cursor-pointer' onClick={() => setQuestionRemove({ ...question, indexQuestion: responseGetAllQuestions?.data?.pageable?.offset + indexQuestion + 1 })}>
                                                    <i className="fas fa-trash-alt fs-6" />
                                                </Badge>
                                            </OverlayTrigger>
                                        </Card.Footer>
                                    </Card>
                                </div>)
                            }
                        </Pagination>
                    </div>
                </div>
            </div>

            <ModalConfirm
                show={questionRemove ? true : false}
                setShow={() => setQuestionRemove(false)}
                title="Confirm"
                message={`Are you sure you want to remove group contain question: "${questionRemove?.indexQuestion}"?`}
                handleNo={() => setQuestionRemove(false)}
                handleYes={handleDeleteQuestionGroup}
            />

            {showCVEQuestion?.show && <Offcanvas placement="start" className="w-full" show={showCVEQuestion?.show} onHide={() => { setQuestionCreateUpdate(false); setShowCVEQuestion({ show: false }) }}>
                <Offcanvas.Header closeButton className="border-bottom">
                    <Offcanvas.Title>
                        {showCVEQuestion?.type === 'update' ? 'Update questions' : showCVEQuestion?.type === 'view' ? 'View questions' : 'Create questions'}
                    </Offcanvas.Title>
                </Offcanvas.Header>
                <Offcanvas.Body className='flex flex-col'>
                    {((showCVEQuestion?.show === true && showCVEQuestion?.newData === true) || showCVEQuestion?.type === 'create') && <Editor
                        value={_.cloneDeep(showCVEQuestion?.data)}
                        onChange={value => showCVEQuestion?.type !== 'view' && setQuestionCreateUpdate(value)}
                    />}

                    {showCVEQuestion?.type !== 'view' &&
                        <Button
                            onClick={handleCreateUpdatCoursae}
                            disabled={questionCreateUpdate ? false : true}
                            className="btn btn-primary w-100 fw-bold mt-3">
                            {showCVEQuestion?.type === 'create' ? 'Create' : 'Update'}
                        </Button>
                    }
                </Offcanvas.Body>
            </Offcanvas>}
            <Footer />
        </Sider>
    </>
}

export default QuestionManagement