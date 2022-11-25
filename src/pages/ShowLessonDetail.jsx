import '../assets/css/ShowLessonDetail.css'
import PagesImage from '../assets/img/pages-image.png'
import { Loader, CourseHeader, CardStudySet, ModalConfirm, RankQuestion } from '../components'
import { useLayoutEffect, useState, useRef } from 'react'
import { getCourseById, updateLessonStatus, showNotFound, hideNotFound } from '../redux/actions'
import { ROUTE_PATH, STATUS_TYPE, PROVIDER } from '../constants'
import { useSelector, useDispatch } from 'react-redux'
import { Accordion } from 'react-bootstrap'
import { History } from '../components/NavigateSetter'
import { useParams } from 'react-router'
import ReactPlayer from 'react-player'
import { Navbar, Container, Offcanvas, Card, Badge, OverlayTrigger, Tooltip } from 'react-bootstrap'
import _ from 'lodash'
import Parser from 'html-react-parser'
import { CommonUtil } from '../utils'
import Timecode from 'react-timecode'

const ShowLessonDetail = (props) => {

    let { courseId, lessonId } = useParams()
    lessonId = lessonId ? parseInt(lessonId) : null

    const heightCourseHeader = useSelector(state => state.UI.CourseHeader.height)
    const isPFetchCourseProcessing = useSelector(state => state.UI.FetchCourseProcessing.isProcessing)

    const dispatch = useDispatch()
    const course = useSelector(state => state.course)
    const isShow = useSelector(state => state.UI.NotFound.isShow)
    const [questionRetest, setQuestionRetest] = useState(false)
    const [showRank, setShowRank] = useState(false)

    useLayoutEffect(() => {
        if (!lessonId || (course && course?.lessons?.every(lesson => lesson !== lessonId))) {
            dispatch(showNotFound())
        }

    }, [dispatch, lessonId, course])

    useLayoutEffect(() => {
        if (_.isEmpty(course) || course?.id !== courseId) {
            dispatch(getCourseById(courseId))
        }

    }, [dispatch, course, courseId])

    const refScrollChapter = useRef(null)
    const refNavbarButtom = useRef(null)
    const refCourseContent = useRef(null)

    const [chapterCurrentShow, setChapterCurrentShow] = useState()
    const [currentIdShowContentCourse, setCurrentIdShowContentCourse] = useState([])
    const [showCourseContent, setShowCourseContent] = useState(true)
    const [previousLesson, setPreviousLesson] = useState()
    const [currentLesson, setCurrentsLesson] = useState()
    const [nextLesson, setNextLesson] = useState()
    const [loadVideo, setloadVideo] = useState()
    const [heightNavbarButtom, setHeightNavbarButtom] = useState(0)
    const [heightCourseContent, setHeightCourseContent] = useState(0)
    const [studysetRetest, setStudysetRetest] = useState(false)

    const removeScrollBody = () => document.body.classList.remove("overflow-hidden")

    useLayoutEffect(() => {
        if (course) {
            const isLessonExist = course?.lessons?.some((lesson, indexLesson, lessons) => {

                if (lesson.id === lessonId) {
                    setCurrentsLesson(lesson)

                    if (indexLesson - 1 >= 0) {
                        setPreviousLesson(lessons[indexLesson - 1])
                    } else {
                        setPreviousLesson(null)
                    }
                    if (indexLesson + 1 < lessons.length) {
                        setNextLesson(lessons[indexLesson + 1])
                    } else {
                        setNextLesson(null)
                    }

                    return true
                }
                return false
            })

            if (isLessonExist) {
                setCurrentIdShowContentCourse(!course ? [] : [course?.chapters?.filter(chapter => chapter.lessons.some(lesson => lesson.id === lessonId))[0]?.id])
                setChapterCurrentShow(course && course?.chapters?.filter(chapter => chapter.lessons.some(lesson => lesson.id === lessonId))[0])

                document.body.classList.add("overflow-hidden")

                if (window.innerWidth < 1024) {
                    setShowCourseContent(false)
                }

                if (refNavbarButtom?.current?.clientHeight) {
                    setHeightNavbarButtom(refNavbarButtom?.current?.clientHeight)
                }

                if (refCourseContent?.current?.clientHeight) {
                    setHeightCourseContent(refCourseContent?.current?.clientHeight)
                }

                dispatch(hideNotFound())
            }
        } else if (!isPFetchCourseProcessing) {
            dispatch(showNotFound())
        }

        return removeScrollBody
    }, [isPFetchCourseProcessing, heightCourseContent, dispatch, course, chapterCurrentShow, lessonId, currentLesson])

    return _.isEmpty(course) || !currentLesson ? <Loader useStateLoader={true} /> : !isShow && <>

        <CourseHeader course={course} onClick={removeScrollBody} />

        <div className='min-h-screen container-fluid'>
            <div className='row'>
                <div className={`${showCourseContent ? 'col-lg-9' : 'col-lg-12'} px-0`}>
                    <div className='overflow-overlay' style={{ maxHeight: `calc(100vh - ${heightNavbarButtom}px - ${heightCourseHeader}px)` }}>

                        <div className='bg-black relative'>
                            <img className="w-full h-auto" alt="img-video" src={PagesImage} style={{ maxHeight: "75vh" }} />

                            <div className='absolute inset-0 bg-black'>
                                <ReactPlayer
                                    controls={true}
                                    light={PagesImage}
                                    playIcon={
                                        <div onClick={() => setloadVideo(true)} className='flex justify-center items-center flex-col'>
                                            <i className="fa-solid text-5xl fa-circle-play rounded-full bg-orange-600 text-white cursor-pointer opacity-95 hover:opacity-100 active:scale-95"></i>
                                        </div>
                                    }
                                    url={currentLesson?.video}
                                    width='100%'
                                    height='100%'
                                    onReady={() => setloadVideo(false)}
                                    onEnded={() => {
                                        if (currentLesson && currentLesson?.status !== STATUS_TYPE.FINISHED) {
                                            dispatch(updateLessonStatus(lessonId, STATUS_TYPE.FINISHED))
                                        }
                                    }}
                                    onStart={() => {
                                        if (currentLesson && currentLesson?.status === STATUS_TYPE.UNFINISHED) {
                                            dispatch(updateLessonStatus(lessonId, STATUS_TYPE.PROCESSING))
                                        }
                                    }}
                                />
                            </div>

                            {loadVideo && <div className='absolute inset-0 flex justify-center items-center bg-black'>
                                <i className="animate-spin text-xl fa-solid fa-spinner text-white"></i>
                            </div>}
                        </div>

                        <div className='px-3'>
                            <div className='font-semibold mt-4 text-2xl'>{currentLesson?.name}</div>
                            <div className='text-gray-500 text-base mt-2.5'>Last updated on {CommonUtil.getDateStringFromMilliseconds(currentLesson?.updateTime || currentLesson?.createTime)}</div>

                            {currentLesson?.description && Parser(currentLesson?.description)}

                            <div className='mt-5 row'>
                                {currentLesson?.lessonExercises?.map(lessonExercise =>
                                    lessonExercise?.provider === PROVIDER.STUDYSET
                                        ? <div className="col-md-6 col-lg-4 mb-3" key={lessonExercise.id}>
                                            <CardStudySet
                                                studyset={lessonExercise?.question}
                                                showHeader={true}
                                                studysetRetest={studysetRetest}
                                                setStudysetRetest={setStudysetRetest}
                                            />
                                        </div>
                                        : lessonExercise?.provider === PROVIDER.QUESTION_BANK
                                            ? <div className="col-md-6 col-lg-4 mb-3" key={lessonExercise.id}>
                                                <Card className="card-study-set-container cursor-default">
                                                    <Card.Body>
                                                        <Card.Title className="title">{lessonExercise?.question?.text}</Card.Title>
                                                        <Card.Subtitle className="mb-2 text-muted">
                                                            <Badge pill bg="warning" text="dark">{lessonExercise?.question?.questionType}</Badge>
                                                        </Card.Subtitle>
                                                        <Card.Text>{CommonUtil.getDateStringFromMilliseconds(lessonExercise?.question?.updateTime || lessonExercise?.question?.createTime)}</Card.Text>
                                                        <div className='flex justify-between items-end'>
                                                            <div className='flex items-center'>
                                                                <i className="fa-solid fa-user-group"></i> <span className='ps-2'>{lessonExercise?.question?.userCount}</span>
                                                            </div>
                                                        </div>
                                                    </Card.Body>

                                                    <Card.Footer className="d-flex justify-content-between">
                                                        <OverlayTrigger placement="bottom" overlay={<Tooltip>Rank</Tooltip>}>
                                                            <Badge bg="primary" className='cursor-pointer' onClick={() => setShowRank({ id: lessonExercise?.question?.id })}>
                                                                <i className="fa-solid fa-ranking-star fs-6" />
                                                            </Badge>
                                                        </OverlayTrigger>

                                                        {
                                                            !lessonExercise?.question?.testResult ?
                                                                <OverlayTrigger placement="bottom" overlay={<Tooltip>Test</Tooltip>}>
                                                                    <Badge bg="warning" className='cursor-pointer' onClick={() => History.push(`${ROUTE_PATH.QUESTION_TEST}/${lessonExercise?.question?.id}`)}>
                                                                        <i className="fas fa-edit fs-6" />
                                                                    </Badge>
                                                                </OverlayTrigger>
                                                                : <OverlayTrigger placement="bottom" overlay={<Tooltip>Score</Tooltip>}>
                                                                    <Badge bg="warning" className='cursor-pointer' onClick={() => setQuestionRetest(lessonExercise?.question)}>
                                                                        <i className="fa-solid fa-eye fs-6" />
                                                                    </Badge>
                                                                </OverlayTrigger>
                                                        }
                                                    </Card.Footer>
                                                </Card>
                                            </div>
                                            : <></>)}
                            </div>
                            <div className='mt-5 text-center py-3 text-gray-500 font-semibold'>Made with <i className="text-red-500 fa-solid fa-heart"></i> · Powered by Do Hung Anh</div>
                        </div>
                    </div>
                </div>
                <div className={`${showCourseContent && window.innerWidth >= 992 ? 'col-lg-3' : 'd-none'} hidden lg:block px-0`}>

                    <div className=''>
                        <div ref={refCourseContent} className='font-bold p-3 tracking-wider'>Course content</div>

                        <div className='overflow-overlay relative p-0' style={{ maxHeight: `calc(100vh - ${heightCourseHeader}px - ${heightNavbarButtom}px - ${heightCourseContent}px)` }}>
                            {
                                course?.chapters.map((chapter, indexChapter) => <Accordion activeKey={currentIdShowContentCourse} ref={chapter.id === chapterCurrentShow?.id ? refScrollChapter : null} key={chapter.id}>
                                    <Accordion.Item eventKey={chapter.id} className="rounded-none">
                                        <Accordion.Header className='flex flex-col ShowLessonDetail-accordion-header bg-gray-100 sticky top-0' onClick={() => setCurrentIdShowContentCourse((previouState => previouState.some(id => id === chapter.id) ? previouState.filter(id => id !== chapter.id) : [...previouState, chapter.id]))}>
                                            <div>
                                                <div>{`${indexChapter + 1}. ${chapter.name}`}</div>
                                                <div className='text-xs text-gray-500 mt-2 tracking-widest'>{chapter.numberOfLessonFinshed}/{chapter.lessons.length} | {chapter.totalDuration}</div>
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
                                                                <div className='flex pe-1'>
                                                                    <span className='pr-2'>{course.chapters.filter((chapter, indexFilter) => indexFilter < indexChapter).reduce((sum, chapter) => sum + chapter.lessons.length, 0) + indexLesson + 1}.</span>
                                                                    <span>{lesson.name}</span>
                                                                </div>
                                                                {(!lesson.status || lesson?.status === STATUS_TYPE.UNFINISHED) && lesson.id !== lessonId
                                                                    ? <div className='ml-auto'><i className="fa-solid fa-lock text-gray-500" /></div>
                                                                    : lesson?.status === STATUS_TYPE.FINISHED
                                                                        ? <div className='ml-auto'><i className="fa-solid fa-circle-check text-lime-500" /></div>
                                                                        : <></>}
                                                            </div>

                                                            <div className='text-xs mt-2'><i className={`me-1 fa-solid fa-circle-play ${((lesson?.status && lesson?.status === STATUS_TYPE.PROCESSING) || lesson.id === lessonId) && "text-orange-500"}`}></i> {lesson.duration}</div>
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

                <Offcanvas className="w-full sm:w-1/2 md:w-5/12 lg:hidden" responsive="lg" show={showCourseContent && window.innerWidth < 992} onHide={() => setShowCourseContent(false)}>
                    <Offcanvas.Header closeButton>
                        <Offcanvas.Title>Course content</Offcanvas.Title>
                    </Offcanvas.Header>
                    <Offcanvas.Body className='overflow-overlay relative p-0'>
                        {
                            course?.chapters.map((chapter, indexChapter) => <Accordion activeKey={currentIdShowContentCourse} ref={chapter.id === chapterCurrentShow?.id ? refScrollChapter : null} key={chapter.id}>
                                <Accordion.Item eventKey={chapter.id} className="rounded-none">
                                    <Accordion.Header className='flex flex-col ShowLessonDetail-accordion-header bg-gray-100 sticky top-0' onClick={() => setCurrentIdShowContentCourse((previouState => previouState.some(id => id === chapter.id) ? previouState.filter(id => id !== chapter.id) : [...previouState, chapter.id]))}>
                                        <div>
                                            <div>{`${indexChapter + 1}. ${chapter.name}`}</div>
                                            <div className='text-xs text-gray-500 mt-2 tracking-widest'>{chapter.numberOfLessonFinshed}/{chapter.lessons.length} | {chapter.totalDuration}</div>
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
                                                            <div className='flex pe-2'>
                                                                <span className='pr-2'>{course.chapters.filter((chapter, indexFilter) => indexFilter < indexChapter).reduce((sum, chapter) => sum + chapter.lessons.length, 0) + indexLesson + 1}.</span>
                                                                <span>{lesson.name}</span>
                                                            </div>
                                                            {(!lesson.status || lesson?.status === STATUS_TYPE.UNFINISHED) && lesson.id !== lessonId
                                                                ? <div className='ml-auto'><i className="fa-solid fa-lock text-gray-500" /></div>
                                                                : lesson?.status === STATUS_TYPE.FINISHED
                                                                    ? <div className='ml-auto'><i className="fa-solid fa-circle-check text-lime-500" /></div>
                                                                    : <></>}
                                                        </div>

                                                        <div className='text-xs mt-2'><i className={`me-1 fa-solid fa-circle-play ${((lesson?.status && lesson?.status === STATUS_TYPE.PROCESSING) || lesson.id === lessonId) && "text-orange-500"}`}></i> {lesson.duration}</div>
                                                    </div>
                                                </div>
                                            </div>
                                        )}
                                    </Accordion.Body>
                                </Accordion.Item>
                            </Accordion>)
                        }
                    </Offcanvas.Body>
                </Offcanvas>
            </div>
        </div >

        <Navbar fixed="bottom" variant="light" className='bg-gray-300' ref={refNavbarButtom}>
            <Container fluid className="lg:flex-row flex_row_reverse">
                <Navbar.Brand className="m-0 py-0 px-3">
                    <div className="flex">
                        {previousLesson && <div onClick={() => History.replace(`${ROUTE_PATH.SHOW_LESSON_DETAIL}/${courseId}/${previousLesson?.id}`)} className='hover:opacity-95 active:scale-95 fw-bold cursor-pointer text-xs border-2 rounded p-2 border-orange-500'><i className="fa-solid fa-chevron-left"></i> PREVIOUS LESSON</div>}
                        {nextLesson && <div onClick={() => History.replace(`${ROUTE_PATH.SHOW_LESSON_DETAIL}/${courseId}/${nextLesson?.id}`)} className={`hover:opacity-95 active:scale-95 fw-bold cursor-pointer text-xs border-2 rounded p-2 border-orange-500 ${previousLesson && 'ms-2'}`}>NEXT LESSON <i className="fa-solid fa-chevron-right"></i></div>}
                    </div>
                </Navbar.Brand>

                <Navbar.Brand className='m-0 px-3 py-0 cursor-pointer'>
                    <div className='flex items-center lg:flex-row md:flex-row-reverse' onClick={() => setShowCourseContent(!showCourseContent)}>
                        <div className='text-base font-semibold lg:pr-3 md:pl-3 d-md-block d-none'>{chapterCurrentShow?.displayName}</div>
                        <div className='flex bg-white w-8 h-8 rounded-full justify-center items-center hover:bg-gray-200 active:scale-95'>
                            <i className={`${`text-base font-bold fa-solid fa-${showCourseContent ? 'arrow-right' : 'bars'}`}`}></i>
                        </div>
                    </div>
                </Navbar.Brand>
            </Container>
        </Navbar>

        <div style={{ height: `${refNavbarButtom?.current?.clientHeight}px` || "0px" }}></div>

        <ModalConfirm
            show={studysetRetest ? true : false}
            setShow={() => setStudysetRetest(false)}
            title={studysetRetest ? `Test: ${studysetRetest?.title}` : 'Closing'}
            message={studysetRetest && <div className='py-2'>
                <div className="py-1"><i className="fa-solid fa-arrows-to-dot"></i> Score: {(studysetRetest?.testResult?.score?.toFixed(2))}</div>
                <div className="py-1"><i className="fa-solid fa-arrows-to-dot"></i> Completion time: <Timecode time={studysetRetest?.testResult?.completionTime} /></div>
                <div className="py-1"><i className="fa-solid fa-arrows-to-dot"></i> Last updated: {CommonUtil.getDateStringFromMilliseconds(studysetRetest?.testResult?.updateTime || studysetRetest?.testResult?.createTime)}</div>
            </div>}
            handleNo={() => setStudysetRetest(false)}
            handleYes={() => History.push(`${ROUTE_PATH.STUDY_SET_TEST}/${studysetRetest?.id}`)}
            labelYes="Retest"
            labelNo="Close"
        />

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

        {showRank?.id && <RankQuestion questionId={showRank?.id} show={showRank ? true : false} onHide={() => setShowRank(false)} />}
    </>
}

export default ShowLessonDetail

/*
    - Chưa làm được cái tự động scroll xuống bài học hiện tại
*/