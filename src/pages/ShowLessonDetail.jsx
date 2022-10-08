import '../assets/css/ShowLessonDetail.css'
import { Loader, CourseHeader } from '../components'
import { useEffect, useState, useRef } from 'react'
import { setUrl } from '../redux/actions'
import { ROUTE_PATH, STATUS_TYPE } from '../constants'
import { useDispatch } from 'react-redux'
import { useGetCourseDetailForUser } from '../hook'
import { Accordion } from 'react-bootstrap'
import { History } from '../components/NavigateSetter'
import { useParams } from 'react-router'
import { NotFound } from '../pages'

import { Navbar, Container } from 'react-bootstrap'

const ShowLessonDetail = (props) => {

    const { courseId, lessonId } = useParams()

    const dispatch = useDispatch()
    const { data: course, isLoading, isFetching } = useGetCourseDetailForUser(courseId)

    useEffect(() => {
        if(course && !isLoading && !isFetching) {
            if (course?.status && course.status !== STATUS_TYPE.UNFINISHED) {
                dispatch(setUrl(ROUTE_PATH.SHOW_LESSON_DETAIL))
            } else if (course?.status && course.status === STATUS_TYPE.UNFINISHED) {
                History.replace(`${ROUTE_PATH.SHOW_COURSE_DETAIL}/${course?.chapterCurrentProcessing?.id}`)
            }            
        }
    }, [ isLoading, course, isFetching, dispatch, course?.status ])

    const refScrollChapter = useRef(null)
    const refButtonScrollChapter = useRef(null)
    const refNavbarButtom= useRef(null)

    const [ chapterCurrentShow, setChapterCurrentShow ] = useState()
    const [ currentIdShowContentCourse, setCurrentIdShowContentCourse ] = useState([])
    const [ showCourseContent, setShowCourseContent ] = useState(true)
    const [ previousLesson, setPreviousLesson ] = useState()
    const [ nextLesson, setNextLesson ] = useState()

    useEffect(() => {
        if(course && !isLoading && !isFetching) {
            setCurrentIdShowContentCourse(!course ? [] : [course?.chapters?.filter(chapter => chapter.lessons.some(lesson => lesson.id === lessonId))[0]?.id])
            setChapterCurrentShow(course && course?.chapters?.filter(chapter => chapter.lessons.some(lesson => lesson.id === lessonId))[0])
            course && course?.lessons?.some((lesson, indexLesson, lessons) => {
                if(lesson.id === lessonId) {
                    if(indexLesson - 1 >= 0) {
                        setPreviousLesson(lessons[indexLesson - 1])
                    } else {
                        setPreviousLesson(null)
                    }
                    if(indexLesson + 1 < lessons.length) {
                        setNextLesson(lessons[indexLesson + 1])
                    } else {
                        setNextLesson(null)
                    }
                    return true
                }
                return false
            })
            refScrollChapter?.current?.scrollIntoView()

            const refSetTimeout = setTimeout(() => {
                refButtonScrollChapter?.current?.click()
            }, 300)
            return () => clearTimeout(refSetTimeout)            
        }

    }, [ course, chapterCurrentShow, lessonId, isFetching, isLoading ])

    return isLoading || isFetching ? <Loader /> : !course ? <NotFound/> : course?.lessons.every(lesson => lesson.id !== lessonId) ? <NotFound/> : <>

        <CourseHeader course={course} />

        <div className='min-h-screen container-fluid'>
            <div className='row'>
                <div className={`${showCourseContent ? 'col-lg-9' : 'col-lg-12'}`}>
                    <div className='min-h-screen'>
                        <button ref={refButtonScrollChapter} className='d-none' onClick={() => window.scrollTo(0, 0)}>Click</button>
                    </div>
                </div>
                <div className={`${showCourseContent ? 'col-lg-3' : 'd-none'}`}>

                    <div className=''>
                        <div className='font-bold py-3.5 px-2.5 tracking-wider'>Course content</div>

                        <div className='max-h-screen overflow-x-scroll relative'>
                            {
                                course?.chapters.map((chapter, indexChapter) => <Accordion activeKey={currentIdShowContentCourse} ref={chapter.id === chapterCurrentShow?.id ? refScrollChapter : null} key={chapter.id}>
                                    <Accordion.Item eventKey={chapter.id} className="rounded-none">
                                        <Accordion.Header className='flex flex-col ShowLessonDetail-accordion-header bg-gray-100 sticky top-0' onClick={() => setCurrentIdShowContentCourse((previouState => previouState.some(id => id === chapter.id) ? previouState.filter(id => id !== chapter.id) : [...previouState, chapter.id]))}>
                                            <div>
                                                <div>{`${indexChapter + 1}. ${chapter.name}`}</div>
                                                <div className='text-xs text-gray-500 mt-1 tracking-widest'>{chapter.numberOfLessonFinshed}/{chapter.lessons.length} | {chapter.totalDuration}</div>
                                            </div>
                                        </Accordion.Header>
                                        <Accordion.Body className='p-0'>
                                            {chapter.lessons.map((lesson, indexLesson) =>
                                                <div onClick={() => History.push(`${ROUTE_PATH.SHOW_LESSON_DETAIL}/${courseId}/${lesson.id}`)} className={`${indexLesson === chapter.lessons.length - 1 ? 'border-b-0' : 'border-b-2'} d-flex justify-content-between p-3 border-slate-200 ${lesson.id === lessonId ? 'bg-red-200' : 'cursor-pointer hover:bg-gray-100'}`} key={lesson.id}>
                                                    <div className={`
                                                    ${!lesson.status || lesson?.status === STATUS_TYPE.UNFINISHED || lesson?.status === STATUS_TYPE.FINISHED
                                                            ? `${lesson.id === lessonId ? 'text-black' : 'text-gray-500'}` : 'text-black'
                                                        } min-w-full`}>
                                                        <div>
                                                            <div className='flex min-w-full'>
                                                                <div>{course.chapters.filter((chapter, indexFilter) => indexFilter < indexChapter).reduce((sum, chapter) => sum + chapter.lessons.length, 0) + indexLesson + 1}. {lesson.name}</div>
                                                                {(!lesson.status || lesson?.status === STATUS_TYPE.UNFINISHED) && lesson.id !== lessonId
                                                                    ? <div className='ml-auto'><i className="fa-solid fa-lock text-gray-500" /></div>
                                                                    : lesson?.status === STATUS_TYPE.FINISHED
                                                                        ? <div className='ml-auto'><i className="fa-solid fa-circle-check text-lime-500" /></div>
                                                                        : <></>}
                                                            </div>

                                                            <div className='text-xs'><i className={`me-1 fa-solid fa-circle-play ${((lesson?.status && lesson?.status === STATUS_TYPE.PROCESSING) || lesson.id === lessonId) && "text-orange-500"}`}></i> {lesson.duration}</div>
                                                        </div>
                                                    </div>
                                                </div>
                                            )}
                                        </Accordion.Body>
                                    </Accordion.Item>
                                </Accordion>)
                            }
                        </div>
                    </div>
                </div>
            </div>
        </div >

        <Navbar fixed="bottom" variant="light" className='bg-gray-300' ref={refNavbarButtom}>
            <Container fluid className="justify-start">
                <Navbar.Brand className="m-0 py-0 px-3">
                    <div className="flex">
                        {previousLesson && <div onClick={() => History.replace(`${ROUTE_PATH.SHOW_LESSON_DETAIL}/${courseId}/${previousLesson?.id}`)} className='hover:opacity-95 active:scale-95 fw-bold cursor-pointer text-xs border-2 rounded p-2 border-orange-500'><i className="fa-solid fa-chevron-left"></i> PREVIOUS LESSON</div>}
                        {nextLesson && <div onClick={() => History.replace(`${ROUTE_PATH.SHOW_LESSON_DETAIL}/${courseId}/${nextLesson?.id}`)} className={`hover:opacity-95 active:scale-95 fw-bold cursor-pointer text-xs border-2 rounded p-2 border-orange-500 ${previousLesson && 'ms-2'}`}>NEXT LESSON <i className="fa-solid fa-chevron-right"></i></div>}
                    </div>
                </Navbar.Brand>

                <Navbar.Brand className='m-0 ms-auto px-3 py-0 cursor-pointer'>
                    <div className='flex items-center' onClick={() => setShowCourseContent(!showCourseContent)}>
                        <div className='text-base font-semibold pe-3'>{chapterCurrentShow?.displayName}</div>
                        <div className='flex bg-white w-8 h-8 rounded-full justify-center items-center hover:bg-gray-200 active:scale-95'>
                            <i className={`${`text-base font-bold fa-solid fa-${showCourseContent ? 'arrow-right' : 'bars'}`}`}></i>
                        </div>
                    </div>
                </Navbar.Brand>
            </Container>
        </Navbar>

        <div style={{ height: `${refNavbarButtom?.current?.clientHeight}px` || "0px" }}></div>
    </>
}

export default ShowLessonDetail

/*
    - Nếu slug === id lessong thì thêm bg-blue-300 (đang active vào)
*/