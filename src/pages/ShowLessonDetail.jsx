import '../assets/css/ShowCourseDetail.css'
import { Footer, CircularProgressBar, Loader } from '../components'
import { useEffect } from 'react'
import { setUrl } from '../redux/actions'
import { ROUTE_PATH } from '../constants'
import { useDispatch } from 'react-redux'
import { Navbar, Container, Nav } from 'react-bootstrap'
import { History } from '../components/NavigateSetter'
import { useGetCourseDetailForUser } from '../hook'

const ShowLessonDetail = (props) => {

    const dispatch = useDispatch()

    useEffect(() => {
        dispatch(setUrl(ROUTE_PATH.SHOW_LESSON_DETAIL))
    }, [dispatch])

    const { data: course, isLoading, isFetching, refetch } = useGetCourseDetailForUser()

    return isLoading || isFetching ? <Loader/> : <>
        <Navbar bg="dark" fixed="top" variant="dark">
            <Container fluid className="justify-start">
                <Navbar.Brand
                    className="fw-bold cursor-pointer me-2"
                    // onClick={() => History.push(ROUTE_PATH.HOME)}
                >
                    <i className="fa-solid fa-chevron-left px-3"/>
                </Navbar.Brand>
                <Navbar.Brand
                    className="fw-bold cursor-pointer me-2 d-none d-sm-block"
                    // onClick={() => History.push(ROUTE_PATH.HOME)}
                >
                    <i className="fas fa-book-reader px-2"/>
                </Navbar.Brand>

                <Navbar.Brand className='text-base font-bold d-none d-lg-block'>{course.name.length > 70 ? course.name.substring(0, 70).concat('...') : course.name}</Navbar.Brand>
                <Navbar.Brand className='text-base font-bold d-none d-md-block d-lg-none'>{course.name.length > 50 ? course.name.substring(0, 50).concat('...') : course.name}</Navbar.Brand>
                <Navbar.Brand className='text-base font-bold d-none d-sm-block d-md-none'>{course.name.length > 30 ? course.name.substring(0, 30).concat('...') : course.name}</Navbar.Brand>
                <Navbar.Brand className='text-base font-bold d-sm-none'>{course.name.length > 20 ? course.name.substring(0, 20).concat('...') : course.name}</Navbar.Brand>
                
                <Navbar.Brand className='m-0 ms-auto px-3 flex justify-center items-center'>
                    <CircularProgressBar
                        colorCircleDone="rgb(148 163 184)"
                        color="rgb(132 204 22)"
                        classNameTextPercent="text-white font-bold"
                        radius={18}
                        strokeWidth={2}
                        percent={course.percent}
                        styleTextPercent={{fontSize: course.percent < 10 ? "10px" : course.percent < 100 ? "9px" : "8px"}}
                    /> <span className='ms-2 text-xs d-none d-sm-block'><span className='font-bold'>{course.numberOfLessonsFinished}/{course.lessons.length}</span> lessons</span>
                </Navbar.Brand>
            </Container>
        </Navbar>

        <div className="min-h-screen container-xl">

        </div>
        <Footer />
    </>
}

export default ShowLessonDetail