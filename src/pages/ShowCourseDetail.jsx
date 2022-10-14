import '../assets/css/ShowCourseDetail.css'
import PagesImage from '../assets/img/pages-image.png'
import { Header, Footer, Loader } from '../components'
import { useEffect, useCallback } from 'react'
import { setUrl } from '../redux/actions'
import { ROUTE_PATH, STATUS_TYPE } from '../constants'
import { useDispatch, useSelector } from 'react-redux'
import { Accordion } from 'react-bootstrap'
import { useState } from 'react'
import { useGetCourseDetailForUser } from '../hook'
import { History } from '../components/NavigateSetter'
import { useParams } from 'react-router'
import { NotFound } from '../pages'

const ShowCourseDetail = (props) => {

    const dispatch = useDispatch()
    const { courseId } = useParams()

    const [currentIdShowContentCourse, setCurrentIdShowContentCourse] = useState([])
    const height = useSelector(state => state.UI.Header.height)

    const { data: course, isLoading: isLoadingGetCourseDetailForUser, isFetching: isFetchingGetCourseDetailForUser, refetch: getCourseDetailForUser } = useGetCourseDetailForUser(courseId)

    const redirectToLessonDetail = useCallback(() => {
        History.replace(`${ROUTE_PATH.SHOW_LESSON_DETAIL}/${courseId}/${course?.lessonCurrentProcessing?.id}`)
    }, [ course?.lessonCurrentProcessing?.id, courseId ])

    useEffect(() => {
        if(course && !isLoadingGetCourseDetailForUser && !isFetchingGetCourseDetailForUser) {
            if(course?.status && course.status === STATUS_TYPE.UNFINISHED) {
                dispatch(setUrl(`${ROUTE_PATH.SHOW_COURSE_DETAIL}/${courseId}`))
            } else if(course?.status && course.status !== STATUS_TYPE.UNFINISHED) {
                redirectToLessonDetail()
            }            
        } else {
            getCourseDetailForUser()
        }
    }, [ redirectToLessonDetail, getCourseDetailForUser, courseId, isLoadingGetCourseDetailForUser, course, isFetchingGetCourseDetailForUser, dispatch, course?.status ])

    return isLoadingGetCourseDetailForUser 
        || isFetchingGetCourseDetailForUser 
        || !course?.status 
        || course.status !== STATUS_TYPE.UNFINISHED 
        ? <Loader /> 
        : !course ? <NotFound/> : <div className='max-h-screen'>
        <Header />

        <div className="min-h-screen container-xl">
            <div className="row">
                <div className="col-lg-8">
                    <div className='mt-4'>
                        <h1 className='font-bold text-3xl'>{course.name}</h1>
                        <p className='text-gray-500'>{course.description}</p>
                    </div>

                    {course?.targets && course?.targets?.length > 0 && <div className='mt-5'>
                        <h2 className='mb-3 text-2xl font-bold'>What will you learn?</h2>
                        <div className="row">
                            {
                                course?.targets.map((target, index) => <div className="col-md-6 mb-4 text-gray-500 d-flex" key={target.id}>
                                    <i className="fa-solid fa-check text-orange-500 leading-4 me-2"></i> <span className='leading-4'>{target.text}</span>
                                </div>)
                            }
                        </div>
                    </div>}

                    <div className='mt-2.5'>

                        <div className='sticky bg-white py-3 z-50' style={{ "top": `${height}px` }}>
                            <h2 className='mb-3 text-2xl font-bold'>Course content</h2>
                            <div className='d-flex justify-content-between'>
                                <div><span className='fw-bold'>{course.chapters.length}</span> <span className='text-gray-500'>chapters</span> <span className='text-gray-500'>•</span> <span className='fw-bold'>{course.lessons.length}</span> <span className='text-gray-500'>lessons</span></div>
                                <div className='cursor-pointer text-orange-500 fw-bold hover:opacity-75' onClick={() => setCurrentIdShowContentCourse((previouState) => previouState.length === 0 ? [...course.chapters.map(chapter => chapter.id)] : [])}>{currentIdShowContentCourse.length === 0 ? 'Expand all' : 'Zoom out all'}</div>
                            </div>
                        </div>

                        <div className="row">
                            {
                                course.chapters.map((chapter, indexChapter) => <Accordion activeKey={currentIdShowContentCourse} key={chapter.id} className="mb-2">
                                    <Accordion.Item eventKey={chapter.id}>
                                        <Accordion.Header className='d-flex ShowCourseDetail-accordion-header bg-gray-100' onClick={() => setCurrentIdShowContentCourse((previouState => previouState.some(id => id === chapter.id) ? previouState.filter(id => id !== chapter.id) : [...previouState, chapter.id]))}>
                                            <span><i className={`fa-solid text-orange-500 ${currentIdShowContentCourse.some(id => id === chapter.id) ? 'fa-minus' : 'fa-plus'}`}></i> {`${indexChapter + 1}. ${chapter.name}`}</span>
                                            <span className='ms-auto text-gray-500 font-normal'>{chapter.lessons.length} lesson</span>
                                        </Accordion.Header>
                                        <Accordion.Body className='py-0'>
                                            {chapter.lessons.map((lesson, indexLesson) =>
                                                <div className={`d-flex justify-content-between py-3 ${indexLesson === chapter.lessons.length-1 ? 'border-b-0' : 'border-b-2'} border-slate-200`} key={lesson.id}>
                                                    <div className='text-gray-500'>
                                                        <i className="fa-regular fa-circle-play text-orange-500"></i> {course.chapters.filter((chapter, indexFilter) => indexFilter < indexChapter).reduce((sum, chapter) => sum + chapter.lessons.length, 0) + indexLesson + 1}. {lesson.name}
                                                    </div>
                                                    <span className='text-gray-700'>{lesson.duration}</span>
                                                </div>
                                            )}
                                        </Accordion.Body>
                                    </Accordion.Item>
                                </Accordion>)
                            }
                        </div>
                    </div>

                    {course?.requests && course.requests.length > 0 && <div className='mt-5'>
                        <h2 className='mb-3 text-2xl font-bold'>Request</h2>
                        <div>
                            {
                                course.requests.map((request, index) => <div className="mb-4 text-gray-500 d-flex" key={request.id}>
                                    <i className="fa-solid fa-check text-orange-500 leading-4 me-2"></i> <span className='leading-4'>{request.text}</span>
                                </div>)
                            }
                        </div>
                    </div>}
                </div>
                <div className="col-lg-4">
                    <div className='pt-4 sticky' style={{ "top": `${height}px` }}>
                        <div className='flex justify-center items-center flex-col'>
                            <div className="rounded-lg relative">
                                <img className="rounded-lg" alt="img-introduce" src={course?.image || PagesImage } />
                                <div className='absolute z-20 inset-0 flex justify-center items-center flex-col'>
                                    <i onClick={redirectToLessonDetail} className="fa-solid text-5xl fa-circle-play rounded-full bg-orange-600 text-white cursor-pointer opacity-95 hover:opacity-100 active:scale-95"></i>
                                </div>
                                <div className='absolute rounded-lg z-10 inset-0 bg-black opacity-25'></div>
                            </div>

                            {course?.price ? <div className='text-4xl pt-4 text-orange-500'>{course.price}</div> : <div className='text-4xl pt-4 text-orange-500'>Free</div>}
                            <div 
                                onClick={redirectToLessonDetail}
                                className="rounded-full px-5 py-2.5 mt-3 text-white uppercase bg-orange-600 hover:opacity-75 cursor-pointer font-bold duration-200 active:-translate-x-0.5 active:translate-y-0.5">Start now</div>
                            <div className='mt-4'>
                                {course?.level && <div className="mb-4 text-gray-500 d-flex">
                                    <i className="fa-solid fa-book-open text-gray-700 leading-4 me-2"></i> <span className='leading-4'>Level: <span className='font-bold text-black'>{course.level}</span></span>
                                </div>}

                                {course?.author && <div className="mb-4 text-gray-500 d-flex">
                                    <i className="fa-solid fa-user text-gray-700 leading-4 me-2"></i> <span className='leading-4'>Author: <span className='font-bold text-black'>{course.author}</span></span>
                                </div>}

                                <div className="mb-4 text-gray-500 d-flex">
                                    <i className="fa-solid fa-book-open text-gray-700 leading-4 me-2"></i> <span className='leading-4'><span>Total <span className='font-bold text-black'>{course.chapters.length}</span> chapters</span> <span className='text-gray-500'>•</span> <span><span className='font-bold text-black'>{course.lessons.length}</span> lessons</span></span>
                                </div>
                                
                                <div className="mb-4 text-gray-500 d-flex">
                                    <i className="fa-solid fa-clock text-gray-700 leading-4 me-2"></i> <span className='leading-4'><span>Duration <span className='font-bold text-black'>{course.totalDuration}</span></span></span>
                                </div>

                                <div className="mb-4 text-gray-500 d-flex">
                                    <i className="fa-solid fa-earth-americas text-gray-700 leading-4 me-2"></i> <span className='leading-4'>Study every time and everywhere</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <Footer />
    </div>
}

export default ShowCourseDetail