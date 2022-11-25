import '../assets/css/UserManagementPage.css'
import '../assets/css/CourseManagement.css'
import { Header, Footer, Sider, Pagination, ModalConfirm, ModalUploadFile, SvelteJSONEditor } from "../components"
import { ROUTE_PATH, STATUS_CODES } from '../constants'
import { Button, Card, Badge, OverlayTrigger, Tooltip, Offcanvas } from 'react-bootstrap'
import { SearchBox, UserInfo, RankQuestion } from '../components'
import { useSelector } from 'react-redux'
import { QuestionApi, MultimediaApi } from '../api'
import { useQuery } from '@tanstack/react-query'
import { useParams } from 'react-router'
import { useLayoutEffect, useCallback, useState } from 'react'
import { useDispatch } from "react-redux"
import { showLoader, hideLoader, showNotFound, hideNotFound } from '../redux/actions'
import _ from 'lodash'
import { Notification, CommonUtil } from '../utils'
import { useDebounce } from 'use-debounce'
import { useSearchParams } from 'react-router-dom'

const baseQuestionCreate = () => ({
    "text": "Test name",
    "time": 10000,
    "pdf": "pdf",
    "answers": [
        {
            "text": "A",
            "audio": "audio"
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
    const [isShowModalUploadFile, setIShowModalUploadFile] = useState(false)
    const [fileUploads, setFileUploads] = useState([])
    const [content, setContent] = useState({ json: baseQuestionCreate() })
    const [ showRank, setShowRank ] = useState(false)
    const [searchParams, setSearchParams] = useSearchParams()
    const [ textSearch ] = useDebounce(searchParams.get('text'), 1000)

    const { data: responseGetAllQuestions, isLoading: isLoadingGetAllQuestions, isFetching: isFetchingGetAllQuestions, isError: isErrorGetAllQuestions, refetch: getAllQuestions } = useQuery(
        ["getAllQuestions", page],
        () => _.isEmpty(searchParams.get('text')) ? QuestionApi.getAll(page, size) : QuestionApi.search(searchParams.get('text'), page, size), {
            refetchOnWindowFocus: false,
        }
    )

    useLayoutEffect (() => {
        refreshPage()
        // eslint-disable-next-line
    }, [ textSearch ])

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
    }, [getAllQuestions, page])

    useLayoutEffect(() => {
        if (showCVEQuestion?.show === true && showCVEQuestion?.type !== 'create' && showCVEQuestion?.newData !== true) {
            dispatch(showLoader())
            QuestionApi.getById(showCVEQuestion?.data?.id)
                .then(res => {
                    const { meta } = res
                    if (meta.code === STATUS_CODES.SUCCESS) {
                        const { data } = res
                        delete data?.testResult
                        setContent({ json: { ...data } })
                        setShowCVEQuestion(previousShowCVEQuestion => ({ ...previousShowCVEQuestion, data: { ...data }, newData: true }))
                        dispatch(hideLoader())
                    } else {
                        setShowCVEQuestion({ show: false })
                        dispatch(hideLoader())
                    }
                })
        }

    }, [showCVEQuestion, dispatch])

    const handleCreateUpdatQuestion = () => {
        // Process create or update
        dispatch(showLoader())
        if (showCVEQuestion?.type === 'create') {
            QuestionApi.create(questionCreateUpdate)
                .then(res => {
                    const { meta } = res
                    if (meta.code === STATUS_CODES.SUCCESS) {
                        refreshPage()
                        setShowCVEQuestion({ show: false })
                        setQuestionCreateUpdate(false)
                        setFileUploads([])
                        Notification.success("Create successfully!")
                    } else {
                        dispatch(hideLoader())
                        Notification.error(meta?.message)
                    }
                })
        } else if (showCVEQuestion?.type === 'update') {
            // Thực hiện tạo cái mới trước
            QuestionApi.update(questionCreateUpdate)
                .then(res => {
                    const { meta } = res
                    if (meta.code === STATUS_CODES.SUCCESS) {
                        refreshPage()
                        setShowCVEQuestion({ show: false })
                        setQuestionCreateUpdate(false)
                        setFileUploads([])
                        Notification.success("Update successfully!")
                    } else {
                        dispatch(hideLoader())
                        Notification.error(meta?.message)
                    }
                })
        }
    }

    const handleDeleteQuestionGroup = () => {
        dispatch(showLoader())
        QuestionApi.deleteById(questionRemove?.id)
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

    const handleUploadFile = (files, allFiles) => {
        dispatch(showLoader())
        MultimediaApi.uploadFile(files?.[0]?.file)
            .then(res => {
                const { meta } = res
                if (meta.code === STATUS_CODES.SUCCESS) {
                    setIShowModalUploadFile(false)
                    setFileUploads([{ ...res?.data, ...files?.[0]?.meta }, ...fileUploads])
                    dispatch(hideLoader())
                } else {
                    dispatch(hideLoader())
                    Notification.error(meta?.message)
                }
            })
    }

    const handleHideCreateUpdate = () => {
        setFileUploads([])
        setQuestionCreateUpdate(false)
        setShowCVEQuestion({ show: false })
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
                                <SearchBox value={searchParams.get('text') || ''} placeholder="Search" onChange={e => setSearchParams(_.isEmpty(e.target.value.trim()) ? {} : { 'text': e.target.value.trim() })} />
                                <Button
                                    className='h-fit font-bold'
                                    onClick={() => {
                                        const jsonCreate = baseQuestionCreate()
                                        setContent({ json: jsonCreate })
                                        setShowCVEQuestion({
                                            type: 'create',
                                            show: true,
                                            data: jsonCreate
                                        })
                                    }}
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
                                            <Card.Title className="title text-lg">{question?.text}</Card.Title>
                                            <Card.Subtitle className="mb-2 text-muted text-sm">
                                                <Badge pill bg="warning" text="dark">{question?.questionType}</Badge>
                                            </Card.Subtitle>
                                            <Card.Text className='text-sm'>{CommonUtil.getDateStringFromMilliseconds(question?.updateTime || question?.createTime)}</Card.Text>
                                            <div className='flex justify-between items-end'>
                                                <div className='flex items-center'>
                                                    <i className="fa-solid fa-user-group"></i> <span className='ps-2'>{question?.userCount}</span>
                                                </div>
                                            </div>
                                        </Card.Body>

                                        <Card.Footer className="d-flex justify-content-between">
                                            <OverlayTrigger placement="bottom" overlay={<Tooltip>Rank</Tooltip>}>
                                                <Badge bg="primary" className='cursor-pointer' onClick={() => setShowRank({id: question?.id})}>
                                                    <i className="fa-solid fa-ranking-star fs-6" />
                                                </Badge>
                                            </OverlayTrigger>

                                            <OverlayTrigger placement="bottom" overlay={<Tooltip>Edit</Tooltip>}>
                                                <Badge bg="warning" className='cursor-pointer' onClick={() => { setShowCVEQuestion({ data: _.cloneDeep(question), show: true, type: 'update' }) }}>
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
                message={`Are you sure you want to remove question: "${questionRemove?.text}"?`}
                handleNo={() => setQuestionRemove(false)}
                handleYes={handleDeleteQuestionGroup}
            />

            {showCVEQuestion?.show && <Offcanvas placement="start" className="w-full" show={showCVEQuestion?.show} onHide={handleHideCreateUpdate}>
                <Offcanvas.Header closeButton className="border-bottom">
                    <Offcanvas.Title>
                        {showCVEQuestion?.type === 'update' ? 'Update question' : showCVEQuestion?.type === 'view' ? 'View question' : 'Create question'}
                    </Offcanvas.Title>
                </Offcanvas.Header>
                <Offcanvas.Body className='flex flex-col'>
                    <SvelteJSONEditor
                        content={content}
                        onChange={value => { setContent(value); setQuestionCreateUpdate(value.json || JSON.parse(value.text)) }}
                        readOnly={showCVEQuestion?.type === 'view' ? true : false}
                    />

                    {showCVEQuestion?.type !== 'view' &&
                        <div className='flex mt-3 fw-bold'>
                            <div className='me-3'>
                                <Button
                                    onClick={() => setIShowModalUploadFile(true)}
                                >Upload file
                                </Button>
                            </div>
                            <div className='grow'>
                                <Button
                                    onClick={handleCreateUpdatQuestion}
                                    disabled={questionCreateUpdate ? false : true}
                                    className="btn btn-primary w-full">
                                    {showCVEQuestion?.type === 'create' ? 'Create' : 'Update'}
                                </Button>
                            </div>
                        </div>
                    }

                    {fileUploads && fileUploads?.length > 0 && <>
                        <h4 className="fw-bold my-5 pb-3 border-bottom border-3 border-start-0 border-end-0 border-top-0 border-danger d-inline-block">Uploaded files</h4>

                        {fileUploads?.map((fileUpload, index) => <SvelteJSONEditor
                            key={index}
                            content={{ json: fileUpload }}
                            readOnly='true'
                        />)}
                    </>}
                </Offcanvas.Body>
            </Offcanvas>}
            <Footer />
        </Sider>

        <ModalUploadFile
            modalTitle="Upload file"
            isShow={isShowModalUploadFile}
            handleSubmit={handleUploadFile}
            accept="application/pdf, audio/*"
            inputContent={(files, extra) => extra.reject ? 'Pdf or audio only' : 'Drag and drop or click to select file'}
            // styles={{
            //     dropzoneReject: { borderColor: 'red', backgroundColor: '#DAA' },
            //     inputLabel: (files, extra) => (extra.reject ? { color: 'red' } : {}),
            // }}
            multiple={false}
            maxFiles={1}
            inputWithFilesContent={files => `${1 - files.length} more`}
            handleClose={() => setIShowModalUploadFile(false)}
        />

        {showRank?.id && <RankQuestion questionId={showRank?.id} show={showRank ? true : false} onHide={() => setShowRank(false)} />}
    </>
}

export default QuestionManagement