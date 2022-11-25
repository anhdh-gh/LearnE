import '../assets/css/UserManagementPage.css'
import '../assets/css/CourseManagement.css'
import PagesImage from '../assets/img/pages-image.png'
import { Header, Footer, Sider, Pagination, ModalConfirm, SvelteJSONEditor, ModalUploadFile } from "../components"
import { ROUTE_PATH, STATUS_CODES } from '../constants'
import { Button, Card, Badge, OverlayTrigger, Tooltip, Offcanvas } from 'react-bootstrap'
import { SearchBox, UserInfo } from '../components'
import { useSelector } from 'react-redux'
import { CourseApi, MultimediaApi } from '../api'
import { useQuery } from '@tanstack/react-query'
import { useParams } from 'react-router'
import { useLayoutEffect, useCallback, useState } from 'react'
import { useDispatch } from "react-redux"
import { showLoader, hideLoader, showNotFound, hideNotFound } from '../redux/actions'
import _ from 'lodash'
import { Notification } from '../utils'
import { useDebounce } from 'use-debounce'
import { useSearchParams } from 'react-router-dom'

const baseCourseCreate = () => ({
    // "name": "",
    // "author": "",
    // "image": "",
    // "description": "",
    // "level": "",
    // "price": "",
    // "chapters": [
    //     {
    //         "name": "",
    //         "lessons": [
    //             {
    //                 "name": "",
    //                 "duration": "",
    //                 "description": "",
    //                 "video": "",
    //                 "lessonExercises": [
    //                     {
    //                         "referenceId": "",
    //                         "provider": "QUESTION_BANK"
    //                     },
    //                     {
    //                         "referenceId": "",
    //                         "provider": "STUDYSET"
    //                     }
    //                 ]
    //             }
    //         ]
    //     }
    // ],
    // "targets": [
    //     {
    //         "text": ""
    //     }
    // ],
    // "requirements": [
    //     {
    //         "text": ""
    //     }
    // ]

    "name": "TOEIC English course",
    "author": "Kim Dung",
    "image": "https://www.testcenter.vn/blog/wp-content/uploads/2020/11/Banner-002.jpg",
    "description": "Toeic English course from basic to advanced, the result of this course is that you have the knowledge of Toeic English to be able to take the exam and get the highest score possible.",
    "level": "Basic",
    "price": "Free",
    "targets": [
        {
            "text": "Mất gốc lấy lại căn bản, tạo cảm hứng"
        },
        {
            "text": "Học cách quản lý thời gian, phân tích đề, tìm hiểu thói quen ra đề của Toeic."
        },
        {
            "text": "Tăng ít nhất 50-100 điểm so với điểm ban đầu."
        },
        {
            "text": "Mở rộng kiến thức, hướng dẫn các tài liệu sát với đề thi nhất."
        }
    ],
    "requirements": [
        {
            "text": "Chủ động học tập và làm theo sự hướng dẫn"
        },
        {
            "text": "Cần có nền tảng tiếng anh căn bản"
        },
        {
            "text": "Tích cực trao đổi với cố giáo và các học viên khác"
        },
        {
            "text": "Làm các bài tập đầy đủ"
        }
    ],
    "chapters": [
        {
            "name": "Chapter name 1",
            "lessons": [
                {
                    "name": "Lesson name 1",
                    "duration": "01:02:15",
                    "description": "Lesson description 1",
                    "video": "https://www.youtube.com/watch?v=C0oooD-OXkk",
                    "lessonExercises": [
                        {
                            "referenceId": "c2176be7-dee1-48ce-805a-4dcf3b7baf75",
                            "provider": "QUESTION_BANK"
                        },
                        {
                            "referenceId": "bde77cee-c3b8-46f3-bb44-fe1e61bdf693",
                            "provider": "STUDYSET"
                        }
                    ]
                }
            ]
        }
    ]
})

const CourseManagement = (props) => {

    const dispatch = useDispatch()
    const { page } = useParams()
    const user = useSelector(state => state.user)
    const size = 8
    const [showCVECourse, setShowCVECourse] = useState({ show: false })
    const [courseRemove, setCourseRemove] = useState(false)
    const [courseCreateUpdate, setCourseCreateUpdate] = useState(false)
    const [content, setContent] = useState({ json: baseCourseCreate() })
    const [isShowModalUploadFile, setIShowModalUploadFile] = useState(false)
    const [fileUploads, setFileUploads] = useState([])
    const [searchParams, setSearchParams] = useSearchParams()
    const [ nameSearch ] = useDebounce(searchParams.get('name'), 1000)

    const { data: responseGetAllCourses, isLoading: isLoadingGetAllCourses, isFetching: isFetchingGetAllCourses, isError: isErrorGetAllCourses, refetch: getAllCourses } = useQuery(
        ["getAllCourses", page],
        () => _.isEmpty(searchParams.get('name')) ? CourseApi.getAll(page, size) : CourseApi.search(page, size, searchParams.get('name')),
        {
            refetchOnWindowFocus: false,
        }
    )

    useLayoutEffect (() => {
        refreshPage()
        // eslint-disable-next-line
    }, [ nameSearch ])

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
        if (showCVECourse?.show === true && showCVECourse?.type !== 'create' && showCVECourse?.newData !== true) {
            dispatch(showLoader())
            CourseApi.handleGetCourseById(showCVECourse?.data?.id)
                .then(res => {
                    const { meta } = res
                    if (meta.code === STATUS_CODES.SUCCESS) {
                        const { data: course } = res
                        setContent({ json: { ...course } })
                        setShowCVECourse(previousShowCVECourse => ({ ...previousShowCVECourse, data: course, newData: true }))
                        dispatch(hideLoader())
                    } else {
                        setShowCVECourse({ show: false })
                        dispatch(hideLoader())
                    }
                })
        }

    }, [showCVECourse, dispatch])

    const handleCreateUpdatCourse = () => {
        dispatch(showLoader())

        if (showCVECourse?.type === 'create') {
            CourseApi.createCourse(courseCreateUpdate)
                .then(res => {
                    const { meta } = res
                    if (meta.code === STATUS_CODES.SUCCESS) {
                        refreshPage()
                        setShowCVECourse({ show: false })
                        setCourseCreateUpdate(false)
                        setFileUploads([])
                        Notification.success("Create successfully!")
                    } else {
                        dispatch(hideLoader())
                        Notification.error(meta?.message)
                    }
                })
        } else if (showCVECourse?.type === 'update') {
            CourseApi.updateCourse(courseCreateUpdate)
                .then(res => {
                    const { meta } = res
                    if (meta.code === STATUS_CODES.SUCCESS) {
                        refreshPage()
                        setShowCVECourse({ show: false })
                        setCourseCreateUpdate(false)
                        setFileUploads([])
                        Notification.success("Update successfully!")
                    } else {
                        dispatch(hideLoader())
                        Notification.error(meta?.message)
                    }
                })
        }
    }

    const handleDeleteCourse = () => {
        dispatch(showLoader())
        CourseApi.deleteCourse(courseRemove?.id)
            .then(res => {
                const { meta } = res
                if (meta.code === STATUS_CODES.SUCCESS) {
                    refreshPage()
                    setCourseRemove(false)
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
        setCourseCreateUpdate(false)
        setShowCVECourse({ show: false })
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
                                <SearchBox value={searchParams.get('name') || ''} placeholder="Search" onChange={e => setSearchParams(_.isEmpty(e.target.value.trim()) ? {} : { 'name': e.target.value.trim() })} />
                                <Button
                                    className='h-fit font-bold'
                                    onClick={() => {
                                        const jsonCreate = baseCourseCreate()
                                        setContent({ json: jsonCreate })
                                        setShowCVECourse({
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
                            hrefPrev={`${ROUTE_PATH.ADMIN_COURSE_VIEW_ALL}/${parseInt(page) - 1}`}
                            hrefNext={`${ROUTE_PATH.ADMIN_COURSE_VIEW_ALL}/${parseInt(page) + 1}`}
                            hrefCurrent={`${ROUTE_PATH.ADMIN_COURSE_VIEW_ALL}/${parseInt(page)}`}
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
                                        <Card.Img className='cursor-pointer border-b' variant="top" src={course?.image || PagesImage} onClick={() => setShowCVECourse({ type: 'view', data: _.cloneDeep(course), show: true })} />
                                        <Card.Body className='cursor-pointer' onClick={() => setShowCVECourse({ type: 'view', data: _.cloneDeep(course), show: true })}>
                                            <Card.Title className="title text-lg">{course?.name}</Card.Title>
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
                                                <Badge bg="primary" className='cursor-pointer' onClick={() => { setShowCVECourse({ data: _.cloneDeep(course), show: true, type: 'update' }) }}>
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
                handleYes={handleDeleteCourse}
            />

            {showCVECourse?.show && <Offcanvas placement="start" className="w-full" show={showCVECourse?.show} onHide={handleHideCreateUpdate}>
                <Offcanvas.Header closeButton className="border-bottom">
                    <Offcanvas.Title>
                        {showCVECourse?.type === 'update' ? 'Update course' : showCVECourse?.type === 'view' ? 'View course' : 'Create course'}
                    </Offcanvas.Title>
                </Offcanvas.Header>
                <Offcanvas.Body className='flex flex-col'>
                    <SvelteJSONEditor
                        content={content}
                        onChange={value => { setContent(value); setCourseCreateUpdate(value.json || JSON.parse(value.text)) }}
                        readOnly={showCVECourse?.type === 'view' ? true : false}
                    />

                    {showCVECourse?.type !== 'view' &&
                        <div className='flex mt-3 fw-bold'>
                            <div className='me-3'>
                                <Button
                                    onClick={() => setIShowModalUploadFile(true)}
                                >Upload file
                                </Button>
                            </div>
                            <div className='grow'>
                                <Button
                                    onClick={handleCreateUpdatCourse}
                                    disabled={courseCreateUpdate ? false : true}
                                    className="btn btn-primary w-full">
                                    {showCVECourse?.type === 'create' ? 'Create' : 'Update'}
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
            // accept="video/*, image/*"
            // inputContent={(files, extra) => extra.reject ? 'Video or image only' : 'Drag and drop or click to select file'}
            // styles={{
            //     dropzoneReject: { borderColor: 'red', backgroundColor: '#DAA' },
            //     inputLabel: (files, extra) => (extra.reject ? { color: 'red' } : {}),
            // }}
            multiple={false}
            maxFiles={1}
            inputWithFilesContent={files => `${1 - files.length} more`}
            handleClose={() => setIShowModalUploadFile(false)}
        />
    </>
}

export default CourseManagement