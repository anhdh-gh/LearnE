import '../assets/css/ShowCourseDetail.css'
import PagesImage from '../assets/img/pages-image.png'
import { Header, Footer } from '../components'
import { useEffect } from 'react'
import { setUrl } from '../redux/actions'
import { ROUTE_PATH } from '../constants'
import { useDispatch, useSelector } from 'react-redux'
import { Accordion } from 'react-bootstrap'
import { useState } from 'react'
import { CommonUtil } from '../utils'

const ShowCourseDetail = (props) => {

    const course = {
        id: "id",
        name: "Làm việc với Terminal & Ubuntu",
        // description: "Khóa học ReactJS từ cơ bản tới nâng cao, kết quả của khóa học này là bạn có thể làm hầu hết các dự án thường gặp với ReactJS. Cuối khóa học này bạn sẽ sở hữu một dự án giống Tiktok.com, bạn có thể tự tin đi xin việc khi nắm chắc các kiến thức được chia sẻ trong khóa học này.",
        // image: "https://files.fullstack.edu.vn/f8-prod/courses/13/13.png",
        // level: "Basic",
        // author: "Đỗ Hùng Anh",
        // extraDataList: [
        //     // ...[...Array(8).keys()].map((extraData, index) => ({
        //     //     id: `Extra data TARGET ${index + 1}`,
        //     //     extraDataKey: "TARGET",
        //     //     extraDataValue: `Biết cách tối ưu hiệu năng ứng dụng ${index + 1}`            
        //     // })),
        //     // ...[...Array(4).keys()].map((extraData, index) => ({
        //     //     id: `Extra data REQUEST ${index + 1}`,
        //     //     extraDataKey: "REQUEST",
        //     //     extraDataValue: `Nắm chắc HTML, CSS, đã có sản phẩm tự tay làm ${index + 1}`            
        //     // })),
        // ],
        chapters: [...Array(3).keys()].map((chapter, index) => ({
            id: `Chapter ${index + 1}`,
            name: `Chapter ${index + 1}`,
            lessons: [...Array(10).keys()].map((lesson, indexLesson) => ({
                id: `Chapter ${index + 1} - Lesson ${indexLesson + 1}`,
                name: `Chapter ${index + 1} - Lesson ${indexLesson + 1}`,
                duration: `02:15` // Not null
            }))
        })), 
        // fee: "1 triệu"
    }

    const dispatch = useDispatch()

    const [currentIdShowContentCourse, setCurrentIdShowContentCourse] = useState([])
    const height = useSelector(state => state.UI.Header.height)

    useEffect(() => {
        dispatch(setUrl(ROUTE_PATH.SHOW_COURSE_DETAIL))
    }, [dispatch])

    return <>
        <Header />

        <div className="min-h-screen container-xl">
            <div className="row">
                <div className="col-lg-8">
                    <div className='mt-4'>
                        <h1 className='font-bold text-3xl'>{course.name}</h1>
                        <p className='text-gray-500'>{course.description}</p>
                    </div>

                    {course?.extraDataList?.some(extraData => extraData.extraDataKey === 'TARGET') && <div className='mt-5'>
                        <h2 className='mb-3 text-2xl font-bold'>What will you learn?</h2>
                        <div className="row">
                            {
                                course?.extraDataList?.filter(extraData => extraData.extraDataKey === 'TARGET').map((extraData, index) => <div className="col-md-6 mb-4 text-gray-500 d-flex" key={extraData.id}>
                                    <i className="fa-solid fa-check text-orange-500 leading-4 me-2"></i> <span className='leading-4'>{extraData.extraDataValue}</span>
                                </div>)
                            }
                        </div>
                    </div>}

                    <div className='mt-2.5'>

                        <div className='sticky bg-white py-3 z-50' style={{ "top": `${height}px` }}>
                            <h2 className='mb-3 text-2xl font-bold'>Course content</h2>
                            <div className='d-flex justify-content-between'>
                                <div><span className='fw-bold'>{course.chapters.length}</span> <span className='text-gray-500'>chapters</span> <span className='text-gray-500'>•</span> <span className='fw-bold'>{course.chapters.reduce((sum, chapter) => sum + chapter.lessons.length, 0)}</span> <span className='text-gray-500'>lessons</span></div>
                                <div className='cursor-pointer text-orange-500 fw-bold hover:opacity-75' onClick={() => setCurrentIdShowContentCourse((previouState) => previouState.length === 0 ? [...course.chapters.map(chapter => chapter.id)] : [])}>{currentIdShowContentCourse.length === 0 ? 'Expand all' : 'Zoom out all'}</div>
                            </div>
                        </div>

                        <div className="row">
                            {
                                course.chapters.map((chapter, indexChapter) => <Accordion activeKey={currentIdShowContentCourse} key={chapter.id} className="mb-2">
                                    <Accordion.Item eventKey={chapter.id}>
                                        <Accordion.Header className='d-flex accordion-header bg-gray-100' onClick={() => setCurrentIdShowContentCourse((previouState => previouState.some(id => id === chapter.id) ? previouState.filter(id => id !== chapter.id) : [...previouState, chapter.id]))}>
                                            <span><i className={`fa-solid text-orange-500 ${currentIdShowContentCourse.some(id => id === chapter.id) ? 'fa-minus' : 'fa-plus'}`}></i> {chapter.name}</span>
                                            <span className='ms-auto text-gray-500 font-normal'>{chapter.lessons.length} lesson</span>
                                        </Accordion.Header>
                                        <Accordion.Body>
                                            {chapter.lessons.map((lesson, indexLesson) =>
                                                <div className='d-flex justify-content-between py-3 border-b-2 border-slate-200' key={lesson.id}>
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

                    {course?.extraDataList?.some(extraData => extraData.extraDataKey === 'REQUEST') && <div className='mt-5'>
                        <h2 className='mb-3 text-2xl font-bold'>Request</h2>
                        <div>
                            {
                                course?.extraDataList?.filter(extraData => extraData.extraDataKey === 'REQUEST').map((extraData, index) => <div className="mb-4 text-gray-500 d-flex" key={extraData.id}>
                                    <i className="fa-solid fa-check text-orange-500 leading-4 me-2"></i> <span className='leading-4'>{extraData.extraDataValue}</span>
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
                                <div className='absolute z-20 inset-0 flex justify-center items-center flex flex-col'>
                                    <i className="fa-solid text-5xl fa-circle-play rounded-full bg-orange-600 text-white cursor-pointer opacity-95 hover:opacity-100 active:scale-95"></i>
                                </div>
                                <div className='absolute rounded-lg z-10 inset-0 bg-black opacity-25'></div>
                            </div>

                            {course?.fee ? <div className='text-4xl pt-4 text-orange-500'>{course.fee}</div> : <div className='text-4xl pt-4 text-orange-500'>Free</div>}
                            <div className="rounded-full px-5 py-2.5 mt-3 text-white uppercase bg-orange-600 hover:opacity-75 cursor-pointer font-bold duration-200 active:-translate-x-0.5 active:translate-y-0.5">Start now</div>
                            <div className='mt-4'>
                                {course?.level && <div className="mb-4 text-gray-500 d-flex">
                                    <i className="fa-solid fa-book-open text-gray-700 leading-4 me-2"></i> <span className='leading-4'>Level: <span className='font-bold text-black'>{course.level}</span></span>
                                </div>}

                                {course?.author && <div className="mb-4 text-gray-500 d-flex">
                                    <i className="fa-solid fa-user text-gray-700 leading-4 me-2"></i> <span className='leading-4'>Author: <span className='font-bold text-black'>{course.author}</span></span>
                                </div>}

                                <div className="mb-4 text-gray-500 d-flex">
                                    <i className="fa-solid fa-book-open text-gray-700 leading-4 me-2"></i> <span className='leading-4'><span>Total <span className='font-bold text-black'>{course.chapters.length}</span> chapters</span> <span className='text-gray-500'>•</span> <span><span className='font-bold text-black'>{course.chapters.reduce((sum, chapter) => sum + chapter.lessons.length, 0)}</span> lessons</span></span>
                                </div>
                                
                                <div className="mb-4 text-gray-500 d-flex">
                                    <i className="fa-solid fa-clock text-gray-700 leading-4 me-2"></i> <span className='leading-4'><span>Duration <span className='font-bold text-black'>{course.chapters.reduce((save, chapter) => [...save, ...chapter.lessons], []).reduce((save, lesson) => CommonUtil.addTimeString(save, lesson.duration), "00:00:00")}</span></span></span>
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
    </>
}

export default ShowCourseDetail