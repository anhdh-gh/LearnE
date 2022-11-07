import '../assets/css/StudySetPage.css'
import PagesImage from '../assets/img/pages-image.png'
import { Header, Footer, SearchBox, UserInfo, Pagination } from '../components'
import { useParams } from 'react-router'
import { CourseApi } from '../api'
import { useLayoutEffect, useCallback } from 'react'
import { useQuery } from '@tanstack/react-query'
import _ from 'lodash'
import { showLoader, hideLoader, showNotFound, hideNotFound } from '../redux/actions'
import { useSelector, useDispatch } from "react-redux"
import { STATUS_CODES, ROUTE_PATH, STATUS_TYPE } from '../constants'
import { History } from '../components/NavigateSetter'
import { Button, Card, Badge } from 'react-bootstrap'

const ShowAllCourse = (props) => {

    const { page } = useParams()
    const dispatch = useDispatch()
    const user = useSelector(state => state.user)

    const size = 9

    const { data: responseGetAllCourse, isLoading, isFetching, isError, refetch: getAllCourse } = useQuery(
        ["getAllCourse", page],
        () => CourseApi.getAll(page, size),
        {
            refetchOnWindowFocus: false,
        }
    )

    const refreshPage = useCallback(() => {
        getAllCourse(page)
    }, [getAllCourse, page])

    useLayoutEffect(() => {
        refreshPage()
    }, [refreshPage])

    useLayoutEffect(() => {
        if (isLoading || isFetching) {
            dispatch(showLoader())
        } else {
            dispatch(hideLoader())
        }

        if (isError || (responseGetAllCourse?.meta && responseGetAllCourse?.meta?.code !== STATUS_CODES.SUCCESS) || parseInt(page) < 0) {
            dispatch(showNotFound())
        } else {
            dispatch(hideNotFound())
        }

        return () => {
            dispatch(hideLoader())
            dispatch(hideNotFound())
        }
    }, [responseGetAllCourse, dispatch, page, isError, isFetching, isLoading])

    return !isLoading && !isFetching && !isError && responseGetAllCourse?.meta?.code === STATUS_CODES.SUCCESS && !_.isEmpty(responseGetAllCourse?.data) && <>
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
                        hrefPrev={`${ROUTE_PATH.SHOW_ALL_COURSE}/${parseInt(page) - 1}`}
                        hrefNext={`${ROUTE_PATH.SHOW_ALL_COURSE}/${parseInt(page) + 1}`}
                        hrefCurrent={`${ROUTE_PATH.SHOW_ALL_COURSE}/${parseInt(page)}`}
                        disabledPrev={responseGetAllCourse?.data?.first}
                        disabledNext={responseGetAllCourse?.data?.last}
                        onClickCurrent={refreshPage}
                        page={parseInt(page) + 1}
                        totalPages={responseGetAllCourse?.data?.totalPages}
                        hide={(responseGetAllCourse?.data && (responseGetAllCourse?.data?.totalElements <= size || _.isEmpty(responseGetAllCourse?.data?.content))) ? true : false}
                    >
                        {
                            responseGetAllCourse?.data?.content?.map((course, index) =>
                                <div className="col-md-6 col-lg-4 mb-3" key={course.id}>
                                    <Card className="card-study-set-container cursor-default">
                                        <Card.Img variant="top" src={course?.image || PagesImage} className='border-b'/>
                                        <Card.Body>
                                            <Card.Title className="title">{course?.name || PagesImage}</Card.Title>
                                            <Card.Subtitle className="mb-2 text-muted">
                                                <Badge pill bg="warning" text="dark">{course?.price}</Badge>
                                            </Card.Subtitle>
                                            <Card.Text>{course?.author}</Card.Text>
                                            <div className='flex justify-between items-end'>
                                                <div className='flex items-end'>
                                                    <span>{course?.level}</span>
                                                </div>
                                                <div className='flex items-center'>
                                                    <i className="fa-solid fa-user-group"></i> <span className='ps-2'>{course?.numberOfPeople}</span>
                                                </div>
                                            </div>
                                        </Card.Body>

                                        <Card.Footer className="d-flex justify-content-between">
                                            <Button className='text-center text-white w-full' onClick={() => History.push(`${ROUTE_PATH.SHOW_COURSE_DETAIL}/${course?.id}`)}>
                                                {course?.status === STATUS_TYPE.UNFINISHED ? 'Start now' : 'Keep studying'}
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

export default ShowAllCourse