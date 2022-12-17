import '../assets/css/StudySetPage.css'
import '../assets/css/UserManagementPage.css'
import '../assets/css/CourseManagement.css'
import { Header, Footer, SearchBox, UserInfo, Pagination, RankQuestion, ModalConfirm } from '../components'
import { useParams } from 'react-router'
import { QuestionApi } from '../api'
import { useLayoutEffect, useCallback, useState } from 'react'
import { useQuery } from '@tanstack/react-query'
import _ from 'lodash'
import { showLoader, hideLoader, showNotFound, hideNotFound } from '../redux/actions'
import { useSelector, useDispatch } from "react-redux"
import { STATUS_CODES, ROUTE_PATH } from '../constants'
import { History } from '../components/NavigateSetter'
import { Button, Card, Badge, Tooltip, OverlayTrigger } from 'react-bootstrap'
import { CommonUtil } from '../utils'
import Timecode from 'react-timecode'
import { useDebounce } from 'use-debounce'
import { useSearchParams } from 'react-router-dom'

const ShowAllQuestion = (props) => {

    const { page } = useParams()
    const dispatch = useDispatch()
    const user = useSelector(state => state.user)
    const [showRank, setShowRank] = useState(false)
    const [questionRetest, setQuestionRetest] = useState(false)
    const [searchParams, setSearchParams] = useSearchParams()
    const [ textSearch ] = useDebounce(searchParams.get('text'), 1000)

    const size = 9
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

    const refreshPage = useCallback(() => {
        getAllQuestions(page)
    }, [getAllQuestions, page])

    useLayoutEffect(() => {
        if (isLoadingGetAllQuestions || isFetchingGetAllQuestions) {
            dispatch(showLoader())
        } else {
            dispatch(hideLoader())
        }

        if (isErrorGetAllQuestions || (responseGetAllQuestions?.meta && responseGetAllQuestions?.meta?.code !== STATUS_CODES.SUCCESS) || parseInt(page) < 0) {
            dispatch(showNotFound())
        } else {
            dispatch(hideNotFound())
        }

        return () => {
            dispatch(hideLoader())
            dispatch(hideNotFound())
        }
    }, [responseGetAllQuestions, dispatch, page, isErrorGetAllQuestions, isFetchingGetAllQuestions, isLoadingGetAllQuestions])

    return !isLoadingGetAllQuestions && !isLoadingGetAllQuestions && !isErrorGetAllQuestions && responseGetAllQuestions?.meta?.code === STATUS_CODES.SUCCESS && !_.isEmpty(responseGetAllQuestions?.data) && <>
        <Header />
        <div className="study-set-page-container">

            {/* Phần các chức năng bên trên */}
            <div className="navigation">
                <div className="container-xl">
                    <div className="row">
                        {!_.isEmpty(user) && <div className="col-md">
                            <UserInfo className="cursor-pointer" limit={30} user={user} />
                        </div>}
                        <div className="col-md d-flex align-items-end justify-content-between mt-4 mt-md-0">
                            <SearchBox value={searchParams.get('text') || ''} placeholder="Search" onChange={e => setSearchParams(_.isEmpty(e.target.value.trim()) ? {} : { 'text': e.target.value.trim() })} />
                            <Button onClick={() => History.push(ROUTE_PATH.HOME)} style={{ height: 'fit-content', fontWeight: 'bold' }}>Home</Button>
                        </div>
                    </div>
                </div>
            </div>

            {/* Phần hiển thị các course */}
            <div className="studysets py-5">
                <div className="container-xl">
                    <Pagination className="row"
                        classNamePagination={`m-0 mt-3`}
                        hrefPrev={`${ROUTE_PATH.ADMIN_QUESTION_VIEW_ALL}/${parseInt(page) - 1}${!_.isEmpty(searchParams.get('text')) ? `?text=${searchParams.get('text')}` : ''}`}
                        hrefNext={`${ROUTE_PATH.ADMIN_QUESTION_VIEW_ALL}/${parseInt(page) + 1}${!_.isEmpty(searchParams.get('text')) ? `?text=${searchParams.get('text')}` : ''}`}
                        hrefCurrent={`${ROUTE_PATH.ADMIN_QUESTION_VIEW_ALL}/${parseInt(page)}${!_.isEmpty(searchParams.get('text')) ? `?text=${searchParams.get('text')}` : ''}`}
                        disabledPrev={responseGetAllQuestions?.data?.first}
                        disabledNext={responseGetAllQuestions?.data?.last}
                        onClickCurrent={refreshPage}
                        page={parseInt(page) + 1}
                        totalPages={responseGetAllQuestions?.data?.totalPages}
                        hide={(responseGetAllQuestions?.data && (responseGetAllQuestions?.data?.totalElements <= size || _.isEmpty(responseGetAllQuestions?.data?.content))) ? true : false}
                    >
                        {
                            responseGetAllQuestions?.data?.content?.map((question, indexQuestion) =>
                                <div className="col-md-6 col-lg-4 mb-3" key={question.id}>
                                    <Card className="card-study-set-container cursor-default">
                                        <Card.Body>
                                            <Card.Title className="title">{question?.text}</Card.Title>
                                            <Card.Subtitle className="mb-2 text-muted">
                                                <Badge pill bg="warning" text="dark">{question?.questionType}</Badge>
                                            </Card.Subtitle>
                                            <Card.Text>{CommonUtil.getDateStringFromMilliseconds(question?.updateTime || question?.createTime)}</Card.Text>
                                            <div className='flex justify-between items-end'>
                                                <div className='flex items-center'>
                                                    <i className="fa-solid fa-user-group"></i> <span className='ps-2'>{question?.userCount}</span>
                                                </div>
                                            </div>
                                        </Card.Body>

                                        <Card.Footer className="d-flex justify-content-between">
                                            <OverlayTrigger placement="bottom" overlay={<Tooltip>Rank</Tooltip>}>
                                                <Badge bg="primary" className='cursor-pointer' onClick={() => setShowRank({ id: question?.id })}>
                                                    <i className="fa-solid fa-ranking-star fs-6" />
                                                </Badge>
                                            </OverlayTrigger>

                                            {
                                                !question?.testResult ?
                                                    <OverlayTrigger placement="bottom" overlay={<Tooltip>Test</Tooltip>}>
                                                        <Badge bg="warning" className='cursor-pointer' onClick={() => History.push(`${ROUTE_PATH.QUESTION_TEST}/${question?.id}`)}>
                                                            <i className="fas fa-edit fs-6" />
                                                        </Badge>
                                                    </OverlayTrigger>
                                                    : <OverlayTrigger placement="bottom" overlay={<Tooltip>Score</Tooltip>}>
                                                        <Badge bg="warning" className='cursor-pointer' onClick={() => setQuestionRetest(question)}>
                                                            <i className="fa-solid fa-eye fs-6" />
                                                        </Badge>
                                                    </OverlayTrigger>
                                            }
                                        </Card.Footer>
                                    </Card>
                                </div>
                            )
                        }
                    </Pagination>
                </div>
            </div>
        </div>
        <Footer />

        {showRank?.id && <RankQuestion questionId={showRank?.id} show={showRank ? true : false} onHide={() => setShowRank(false)} />}

        <ModalConfirm
            show={questionRetest ? true : false}
            setShow={() => setQuestionRetest(false)}
            title={questionRetest ? `Test: ${questionRetest?.text}` : 'Closing'}
            message={questionRetest && <div className='py-2'>
                <div className="py-1"><i className="fa-solid fa-arrows-to-dot"></i> Score: {(questionRetest?.testResult?.score?.toFixed(2))}</div>
                <div className="py-1"><i className="fa-solid fa-arrows-to-dot"></i> Completion time: <Timecode time={questionRetest?.testResult?.completionTime} /></div>
                <div className="py-1"><i className="fa-solid fa-arrows-to-dot"></i> Last updated: {CommonUtil.getDateStringFromMilliseconds(questionRetest?.testResult?.updateTime || questionRetest?.testResult?.createTime)}</div>
            </div>}
            handleNo={() => setQuestionRetest(false)}
            handleYes={() => History.push(`${ROUTE_PATH.QUESTION_TEST}/${questionRetest?.id}`)}
            labelYes="Retest"
            labelNo="Close"
        />
    </>
}

export default ShowAllQuestion