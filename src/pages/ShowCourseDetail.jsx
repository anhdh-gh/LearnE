import '../assets/css/ShowCourseDetail.css'
import { Header, Footer } from '../components'
import { useEffect } from 'react'
import { setUrl } from '../redux/actions'
import { ROUTE_PATH } from '../constants'
import { useDispatch, useSelector } from 'react-redux'
import { Accordion } from 'react-bootstrap'
import { useState } from 'react'

const ShowCourseDetail = (props) => {

    const dispatch = useDispatch()

    const [currentIndexShowContentCourse, setCurrentIndexShowContentCourse] = useState([])
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
                        <h1 className='font-bold text-3xl'>Làm việc với Terminal & Ubuntu</h1>
                        <p className='text-gray-500'>Khóa học ReactJS từ cơ bản tới nâng cao, kết quả của khóa học này là bạn có thể làm hầu hết các dự án thường gặp với ReactJS. Cuối khóa học này bạn sẽ sở hữu một dự án giống Tiktok.com, bạn có thể tự tin đi xin việc khi nắm chắc các kiến thức được chia sẻ trong khóa học này.</p>
                    </div>

                    <div className='mt-5'>
                        <h2 className='mb-3 text-2xl font-bold'>What will you learn?</h2>
                        <div className="row">
                            {
                                [...Array(14).keys()].map((item, index) => <div className="col-md-6 mb-4 text-gray-500 d-flex" key={index}>
                                    <i className="fa-solid fa-check text-orange-500 leading-4 me-2"></i> <span className='leading-4'>Biết cách tối ưu hiệu năng ứng dụng {index}</span>
                                </div>)
                            }
                        </div>
                    </div>

                    <div className='mt-2.5'>

                        <div className='sticky bg-white py-3 z-50' style={{ "top": `${height}px` }}>
                            <h2 className='mb-3 text-2xl font-bold'>Course content</h2>
                            <div className='d-flex justify-content-between'>
                                <div><span className='fw-bold'>20</span> <span className='text-gray-500'>chapters</span> <span className='text-gray-500'>•</span> <span className='fw-bold'>119</span> <span className='text-gray-500'>lessons</span></div>
                                <div className='cursor-pointer text-orange-500 fw-bold hover:opacity-75' onClick={() => setCurrentIndexShowContentCourse((previouState) => previouState.length === 0 ? [...Array(14).keys()] : [])}>{currentIndexShowContentCourse.length === 0 ? 'Expand all' : 'Zoom out all'}</div>
                            </div>
                        </div>

                        <div className="row">
                            {
                                [...Array(14).keys()].map((item, index) => <Accordion activeKey={currentIndexShowContentCourse} key={index} className="mb-2">
                                    <Accordion.Item eventKey={index}>
                                        <Accordion.Header className='d-flex accordion-header bg-gray-100' onClick={() => setCurrentIndexShowContentCourse((previouState => previouState.some(idx => idx === index) ? previouState.filter(idx => idx !== index) : [...previouState, index]))}>
                                            <span><i className={`fa-solid text-orange-500 ${currentIndexShowContentCourse.some(idx => idx === index) ? 'fa-minus' : 'fa-plus'}`}></i> Accordion Item {index}</span>
                                            <span className='ms-auto text-gray-500 font-normal'>{index} lesson</span>
                                        </Accordion.Header>
                                        <Accordion.Body>
                                            {[...Array(5).keys()].map((item, index) =>
                                                <div className='d-flex justify-content-between py-3 border-b-2 border-slate-200' key={index}>
                                                    <div className='text-gray-500'>
                                                        <i className="fa-regular fa-circle-play text-orange-500"></i> {index + 1}. Thực hành tạo Arrow function
                                                    </div>
                                                    <span className='text-gray-700'>02:15</span>
                                                </div>
                                            )}
                                        </Accordion.Body>
                                    </Accordion.Item>
                                </Accordion>)
                            }
                        </div>
                    </div>

                    <div className='mt-5'>
                        <h2 className='mb-3 text-2xl font-bold'>Request</h2>
                        <div>
                            {
                                [...Array(5).keys()].map((item, index) => <div className="mb-4 text-gray-500 d-flex" key={index}>
                                    <i className="fa-solid fa-check text-orange-500 leading-4 me-2"></i> <span className='leading-4'>Nắm chắc HTML, CSS, đã có sản phẩm tự tay làm {index}</span>
                                </div>)
                            }
                        </div>
                    </div>
                </div>
                <div className="col-lg-4">
                    <div className='pt-4 sticky' style={{ "top": `${height}px` }}>
                        <div className='flex justify-center items-center flex-col'>
                            <div className="rounded-lg relative">
                                <img className="rounded-lg" alt="img-introduce" src="https://files.fullstack.edu.vn/f8-prod/courses/13/13.png" />
                                <div className='absolute z-20 inset-0 flex justify-center items-center flex flex-col'>
                                    <i className="fa-solid text-5xl fa-circle-play text-white cursor-pointer opacity-95 hover:opacity-100 active:scale-95"></i>
                                    <div className='text-2xl text-white font-bold cursor-pointer opacity-95 hover:opacity-100 active:scale-95'>Start</div>
                                </div>
                                <div className='absolute rounded-lg z-10 inset-0 bg-black opacity-25'></div>
                            </div>

                            <div className='text-4xl pt-4 text-orange-500'>Free</div>
                            <div className="rounded-full px-5 py-2.5 mt-3 text-white uppercase bg-orange-600 hover:opacity-75 cursor-pointer font-bold duration-200 active:-translate-x-0.5 active:translate-y-0.5">Start now</div>
                            <div className='mt-4'>
                                <div className="mb-4 text-gray-500 d-flex">
                                    <i className="fa-solid fa-layer-group text-gray-700 leading-4 me-2"></i> <span className='leading-4'>Basic qualifications</span>
                                </div>

                                <div className="mb-4 text-gray-500 d-flex">
                                    <i className="fa-solid fa-book-open text-gray-700 leading-4 me-2"></i> <span className='leading-4'><span>Total <span className='font-bold text-black'>20</span> chapters</span> </span>
                                </div>

                                <div className="mb-4 text-gray-500 d-flex">
                                    <i className="fa-solid fa-clapperboard text-gray-700 leading-4 me-2"></i> <span className='leading-4'><span>Total <span className='font-bold text-black'>119</span> lessons</span></span>
                                </div>
                                
                                <div className="mb-4 text-gray-500 d-flex">
                                    <i className="fa-solid fa-clock text-gray-700 leading-4 me-2"></i> <span className='leading-4'><span>Duration <span className='font-bold text-black'>12 hours 10 minutes</span></span></span>
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