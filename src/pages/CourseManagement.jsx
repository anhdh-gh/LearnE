import '../assets/css/UserManagementPage.css'
import '../assets/css/CourseManagement.css'
import 'jsoneditor-react/es/editor.min.css'
import { JsonEditor as Editor } from 'jsoneditor-react'
import PagesImage from '../assets/img/pages-image.png'
import { Header, Footer, Sider, Pagination, ModalConfirm } from "../components"
import { ROUTE_PATH, STATUS_CODES } from '../constants'
import { Button, Card, Badge, OverlayTrigger, Tooltip, Offcanvas } from 'react-bootstrap'
import { SearchBox, UserInfo } from '../components'
import { useSelector } from 'react-redux'
import { History } from '../components/NavigateSetter'
import { CourseApi } from '../api'
import { useQuery } from '@tanstack/react-query'
import { useParams } from 'react-router'
import { useLayoutEffect, useCallback, useState } from 'react'
import { useDispatch } from "react-redux"
import { showLoader, hideLoader, showNotFound, hideNotFound } from '../redux/actions'
import _ from 'lodash'
// import { Notification } from '../utils'

const CourseManagement = (props) => {

    const dispatch = useDispatch()
    const { page } = useParams()
    const user = useSelector(state => state.user)
    const size = 8
    const [showVECourse, setShowVECourse] = useState({ show: false })
    const [courseRemove, setCourseRemove] = useState(false)
    const [courseUpdate, setCourseUpdate] = useState(false)

    const { data: responseGetAllCourses, isLoading: isLoadingGetAllCourses, isFetching: isFetchingGetAllCourses, isError: isErrorGetAllCourses, refetch: getAllCourses } = useQuery(
        ["getAllCourses", page],
        () => CourseApi.getAll(page, size),
        {
            refetchOnWindowFocus: false,
        }
    )

    useLayoutEffect(() => {
        if (isLoadingGetAllCourses || isFetchingGetAllCourses) {
            dispatch(showLoader())
        } else {
            dispatch(hideLoader())
        }

        if (isErrorGetAllCourses || (responseGetAllCourses?.meta && responseGetAllCourses?.meta?.code !== STATUS_CODES.SUCCESS)) {
            dispatch(showNotFound())
        } else {
            dispatch(hideNotFound())
        }

        return () => {
            dispatch(hideLoader())
            dispatch(hideNotFound())
        }
    }, [responseGetAllCourses, dispatch, isErrorGetAllCourses, isFetchingGetAllCourses, isLoadingGetAllCourses])

    const refreshPage = useCallback(() => {
        getAllCourses(page)
    }, [getAllCourses, page])

    useLayoutEffect(() => {
        refreshPage()
    }, [refreshPage])

    useLayoutEffect(() => {
        if(showVECourse?.show === true && _.isEmpty(showVECourse?.data?.chapters)) {
            dispatch(showLoader())
            CourseApi.handleGetCourseById(showVECourse?.data?.id)
            .then(res => {
                const { meta } = res
                if(meta.code === STATUS_CODES.SUCCESS) {
                    const { data: course } = res
                    setShowVECourse(previousShowVECourse => ({...previousShowVECourse, data: course}))
                    dispatch(hideLoader()) 
                } else {
                    setShowVECourse({show: false})
                    dispatch(hideLoader())
                }
            })            
        }

    }, [showVECourse, dispatch])

    const handleUpdatCoursae = () => {
        // dispatch(showLoader())
        // CourseApi.updateUser(userUpdate?.id, userUpdate?.role)
        // .then(res => {
        //     const { meta } = res
        //     if(meta.code === STATUS_CODES.SUCCESS) {
        //         // const { data: user } = res
        //         dispatch(hideLoader()) 
        //         refreshPage()
        //         setShowVECourse({ show: false })
        //         setUserUpdate(false)
        //         Notification.success("Updated successfully!")
        //     } else {
        //         dispatch(hideLoader())
        //         Notification.error('Error, try again!')
        //     }
        // })
    }

    return !isLoadingGetAllCourses && !isFetchingGetAllCourses && !isErrorGetAllCourses && responseGetAllCourses?.meta?.code === STATUS_CODES.SUCCESS && !_.isEmpty(responseGetAllCourses?.data) && <>
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
                                <Button className='h-fit font-bold' onClick={() => History.push(`${ROUTE_PATH.ADMIN_USER_VIEW_ALL}/0`)}>Home</Button>
                            </div>
                        </div>
                    </div>
                </div>

                <div className="content pb-3">
                    <div className="container-xl">
                        <Pagination className="row"
                            classNamePagination={`m-0 mt-3`}
                            hrefPrev={`${ROUTE_PATH.ADMIN_USER_VIEW_ALL}/${parseInt(page) - 1}`}
                            hrefNext={`${ROUTE_PATH.ADMIN_USER_VIEW_ALL}/${parseInt(page) + 1}`}
                            hrefCurrent={`${ROUTE_PATH.ADMIN_USER_VIEW_ALL}/${parseInt(page)}`}
                            disabledPrev={responseGetAllCourses?.data?.first}
                            disabledNext={responseGetAllCourses?.data?.last}
                            onClickCurrent={refreshPage}
                            page={parseInt(page) + 1}
                            totalPages={responseGetAllCourses?.data?.totalPages}
                            hide={(responseGetAllCourses?.data && (responseGetAllCourses?.data?.totalElements <= size || _.isEmpty(responseGetAllCourses?.data?.content))) ? true : false}
                        >
                            {
                                responseGetAllCourses?.data?.content?.map(course => <div key={course?.id} className="col-sm-6 col-md-4 col-lg-3 mt-3">
                                    <Card className="card-user cursor-default">
                                        <Card.Img className='cursor-pointer border-b' variant="top" src={course?.image || PagesImage} onClick={() => setShowVECourse({ type: 'view', data: _.cloneDeep(course), show: true })} />
                                        <Card.Body className='cursor-pointer' onClick={() => setShowVECourse({ type: 'view', data: _.cloneDeep(course), show: true })}>
                                            <Card.Title className="title">{course?.name}</Card.Title>
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
                                            <OverlayTrigger placement="bottom" overlay={<Tooltip>Edit</Tooltip>}>
                                                <Badge bg="primary" className='cursor-pointer' onClick={() => { setShowVECourse({ data: _.cloneDeep(course), show: true, type: 'update' }) }}>
                                                    <i className="fas fa-edit fs-6" />
                                                </Badge>
                                            </OverlayTrigger>

                                            <OverlayTrigger placement="bottom" overlay={<Tooltip>Remove</Tooltip>}>
                                                <Badge bg="danger" className='cursor-pointer' onClick={() => setCourseRemove({ ...course })}>
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
                show={courseRemove ? true : false}
                setShow={() => setCourseRemove(false)}
                title="Confirm"
                message={`Are you sure you want to remove course: "${courseRemove?.name}"?`}
                handleNo={() => setCourseRemove(false)}
                handleYes={() => setCourseRemove(false)}
            />

            {showVECourse?.show && <Offcanvas placement="start" className="w-full" show={showVECourse?.show} onHide={() => { setCourseUpdate(false); setShowVECourse({ show: false }) }}>
                <Offcanvas.Header closeButton className="border-bottom">
                    <Offcanvas.Title>
                        {showVECourse?.type === 'update' ? 'Update course' : 'View course'}
                    </Offcanvas.Title>
                </Offcanvas.Header>
                <Offcanvas.Body className='flex flex-col'>
                    {/* {
                        !isLoadingGetCourseDetail && !isFetchingGetCourseDetail && !isErrorGetCourseDetail && responseGetCourseDetail?.meta?.code === STATUS_CODES.SUCCESS && !_.isEmpty(responseGetCourseDetail?.data)
                            ? <>
                                <Editor
                                    value={responseGetCourseDetail?.data}
                                    onChange={value => showVECourse?.type === 'update' && setCourseUpdate(value)}
                                />

                                {showVECourse?.type === 'update' &&
                                    <Button
                                        onClick={handleUpdatCoursae}
                                        disabled={courseUpdate ? false : true}
                                        className="btn btn-primary w-100 fw-bold mt-3">
                                        Update
                                    </Button>
                                }
                            </> : <Loader forComponent={true}></Loader>
                    } */}

                    {showVECourse?.show === true && !_.isEmpty(showVECourse?.data?.chapters) && <Editor
                        value={showVECourse?.data}
                        onChange={value => showVECourse?.type === 'update' && setCourseUpdate(value)}
                    />}

                    {showVECourse?.type === 'update' &&
                        <Button
                            onClick={handleUpdatCoursae}
                            disabled={courseUpdate ? false : true}
                            className="btn btn-primary w-100 fw-bold mt-3">
                            Update
                        </Button>
                    }
                </Offcanvas.Body>
            </Offcanvas>}

            <Footer />
        </Sider>
    </>
}

export default CourseManagement