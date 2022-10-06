import '../assets/css/ShowLessonDetail.css'
import { Footer, Loader, CourseHeader } from '../components'
import { useEffect, useState } from 'react'
import { setUrl } from '../redux/actions'
import { ROUTE_PATH, STATUS_TYPE } from '../constants'
import { useDispatch } from 'react-redux'
import { useGetCourseDetailForUser } from '../hook'
import { Accordion } from 'react-bootstrap'
import { History } from '../components/NavigateSetter'

const ShowLessonDetail = (props) => {

    const dispatch = useDispatch()

    const { data: course, isLoading, isFetching } = useGetCourseDetailForUser()

    useEffect(() => {
        if(course?.status && course.status !== STATUS_TYPE.UNFINISHED) {
            dispatch(setUrl(ROUTE_PATH.SHOW_LESSON_DETAIL))
        } else if(course?.status && course.status === STATUS_TYPE.UNFINISHED) {
            History.replace(ROUTE_PATH.SHOW_COURSE_DETAIL)
        }
    }, [ dispatch, course?.status ])

    const [ currentIdShowContentCourse, setCurrentIdShowContentCourse ] = useState([])

    useEffect(() => {
        setCurrentIdShowContentCourse((!course || !course?.chapters) ? [] : [...[course.chapters.filter(
            chapter => chapter.lessons.some(chapter => chapter.status === STATUS_TYPE.PROCESSING))[0]]
            .map(chapter => chapter.id)
        ])
    }, [ course, course?.chapters ])

    return isLoading || isFetching ? <Loader/> : <>
        <CourseHeader course={course} />

        <div className="min-h-screen container-fluid">
            <div className='row'>
                <div className='col-lg-9'>
                    <div className='min-h-screen'></div>
                </div>
                <div className='col-lg-3'>
                    <div className=''>
                        <div className='font-bold py-3.5 px-2.5 tracking-wider'>Course content</div>

                        <div className='max-h-screen overflow-x-scroll relative'>
                            {course.chapters.map((chapter, indexChapter) => <Accordion activeKey={currentIdShowContentCourse} key={chapter.id} className="">
                                <Accordion.Item eventKey={chapter.id} className="rounded-none">
                                    <Accordion.Header className='flex flex-col ShowLessonDetail-accordion-header bg-gray-100 sticky top-0' onClick={() => setCurrentIdShowContentCourse((previouState => previouState.some(id => id === chapter.id) ? previouState.filter(id => id !== chapter.id) : [...previouState, chapter.id]))}>
                                        <div>
                                            <div>{`${indexChapter + 1}. ${chapter.name}`}</div>
                                            <div className='text-xs text-gray-500 mt-1 tracking-widest'>{chapter.numberOfLessonFinshed}/{chapter.lessons.length} | {chapter.totalDuration}</div>                                    
                                        </div>
                                    </Accordion.Header>
                                    <Accordion.Body className='py-0'>
                                        {chapter.lessons.map((lesson, indexLesson) =>
                                            <div className={`${indexLesson === chapter.lessons.length-1 ? 'border-b-0' : 'border-b-2'} d-flex justify-content-between py-3 border-slate-200`} key={lesson.id}>
                                                <div className={`
                                                    ${!lesson.status || lesson?.status === STATUS_TYPE.UNFINISHED || lesson?.status === STATUS_TYPE.FINISHED
                                                        ? 'text-gray-500' : 'text-black'
                                                } min-w-full`}>
                                                    <div>
                                                        <div className='flex min-w-full'>
                                                            <div>{course.chapters.filter((chapter, indexFilter) => indexFilter < indexChapter).reduce((sum, chapter) => sum + chapter.lessons.length, 0) + indexLesson + 1}. {lesson.name}</div>
                                                            {!lesson.status || lesson?.status === STATUS_TYPE.UNFINISHED
                                                            ? <div className='ml-auto'><i className="fa-solid fa-lock text-gray-500"/></div>
                                                            : lesson?.status === STATUS_TYPE.FINISHED
                                                            ? <div className='ml-auto'><i className="fa-solid fa-circle-check text-lime-500"/></div>
                                                            : <></>}
                                                        </div>        

                                                        <div className='text-xs'><i className={`fa-solid fa-circle-play ${lesson?.status && lesson?.status === STATUS_TYPE.PROCESSING && "text-orange-500"}`}></i> {lesson.duration}</div>                                                
                                                    </div>
                                                </div>
                                            </div>
                                        )}
                                    </Accordion.Body>
                                </Accordion.Item>
                            </Accordion>)}       
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <Footer />
    </>
}

export default ShowLessonDetail