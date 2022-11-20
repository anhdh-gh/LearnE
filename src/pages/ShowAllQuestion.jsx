import '../assets/css/StudySetPage.css'
import '../assets/css/UserManagementPage.css'
import '../assets/css/CourseManagement.css'
import { Header, Footer, SearchBox, UserInfo, Pagination } from '../components'
import { useParams } from 'react-router'
import { QuestionApi } from '../api'
import { useLayoutEffect, useCallback } from 'react'
import { useQuery } from '@tanstack/react-query'
import _ from 'lodash'
import { showLoader, hideLoader, showNotFound, hideNotFound } from '../redux/actions'
import { useSelector, useDispatch } from "react-redux"
import { STATUS_CODES, ROUTE_PATH, QUESTION_TYPE } from '../constants'
import { History } from '../components/NavigateSetter'
import { Button, Card, Badge } from 'react-bootstrap'
import { CommonUtil } from '../utils'

const ShowAllQuestion = (props) => {

    const { page } = useParams()
    const dispatch = useDispatch()
    const user = useSelector(state => state.user)

    const size = 9
    const { data: responseGetAllQuestions, isLoading: isLoadingGetAllQuestions, isFetching: isFetchingGetAllQuestions, isError: isErrorGetAllQuestions, refetch: getAllQuestions } = useQuery(
        ["getAllQuestions", page],
        () => QuestionApi.getAll(page, size),
        {
            refetchOnWindowFocus: false,
        }
    )

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
                            <SearchBox placeholder="Search" />
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
                            responseGetAllQuestions?.data?.content?.map((question, indexQuestion) =>
                                <div className="col-md-6 col-lg-4 mb-3" key={question.id}>
                                    <Card className="card-study-set-container cursor-default">
                                        <Card.Body>
                                            <Card.Title className="title">{question?.questionType === QUESTION_TYPE.FILE ? question?.text : `Question ${responseGetAllQuestions?.data?.pageable?.offset + indexQuestion + 1}`}</Card.Title>
                                            <Card.Subtitle className="mb-2 text-muted">
                                                <Badge pill bg="warning" text="dark">{question?.questionType}</Badge>
                                            </Card.Subtitle>
                                            <Card.Text>{CommonUtil.getDateStringFromMilliseconds(question?.updateTime || question?.createTime)}</Card.Text>
                                        </Card.Body>

                                        <Card.Footer className="d-flex justify-content-between">
                                            <Button className='text-center text-white w-full' onClick={() => History.push(`${question?.questionType === QUESTION_TYPE.FILE && ROUTE_PATH.TEST_FILE_QUESTION}/${question?.groupId}`)}>
                                                {/* {course?.status === STATUS_TYPE.UNFINISHED ? 'Start now' : 'Keep studying'} */}
                                                Start now
                                            </Button>
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
    </>
}

export default ShowAllQuestion