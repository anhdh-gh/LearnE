import { Navbar, Container } from 'react-bootstrap'
import { History } from '../components/NavigateSetter'
import { CircularProgressBar } from '../components'
import { useRef, useLayoutEffect  } from 'react'
import { setHeightCourseHeader } from '../redux/actions'
import { useSelector, useDispatch } from 'react-redux'
import { ROUTE_PATH } from '../constants'

const  CourseHeader = (props) => {

    const { course, onClick } = props

    const refHeader = useRef(null)
    const dispatch = useDispatch()
    const height = useSelector(state => state.UI.CourseHeader.height)

    useLayoutEffect (() => {
        dispatch(setHeightCourseHeader(refHeader.current.clientHeight))
    }, [dispatch])

    return <>
        <Navbar onClick={onClick} ref={refHeader} bg="dark" fixed="top" variant="dark">
            <Container fluid className="justify-start">
                <Navbar.Brand
                    className="fw-bold cursor-pointer me-2 py-0"
                onClick={() => History.push(`${ROUTE_PATH.SHOW_ALL_COURSE}/0`)}
                >
                    <i className="fa-solid fa-chevron-left px-3" />
                </Navbar.Brand>
                <Navbar.Brand
                    className="fw-bold cursor-pointer me-2 d-none d-sm-block py-0"
                onClick={() => History.push(`${ROUTE_PATH.SHOW_ALL_COURSE}/0`)}
                >
                    <i className="fas fa-book-reader px-2" />
                </Navbar.Brand>

                <Navbar.Brand className='text-base font-bold d-none d-lg-block py-0'>{course.name.length > 70 ? course.name.substring(0, 70).concat('...') : course.name}</Navbar.Brand>
                <Navbar.Brand className='text-base font-bold d-none d-md-block d-lg-none py-0'>{course.name.length > 50 ? course.name.substring(0, 50).concat('...') : course.name}</Navbar.Brand>
                <Navbar.Brand className='text-base font-bold d-none d-sm-block d-md-none py-0'>{course.name.length > 30 ? course.name.substring(0, 30).concat('...') : course.name}</Navbar.Brand>
                <Navbar.Brand className='text-base font-bold d-sm-none'>{course.name.length > 20 ? course.name.substring(0, 20).concat('...') : course.name}</Navbar.Brand>

                <Navbar.Brand className='m-0 ms-auto px-3 flex justify-center items-center py-0'>
                    <CircularProgressBar
                        colorCircleDone="rgb(148 163 184)"
                        color="rgb(132 204 22)"
                        classNameTextPercent="text-white font-bold"
                        radius={18}
                        strokeWidth={2}
                        percent={course.percent}
                        styleTextPercent={{ fontSize: course.percent < 10 ? "10px" : course.percent < 100 ? "9px" : "8px" }}
                    /> <span className='ms-2 text-xs d-none d-sm-block'><span className='font-bold'>{course.numberOfLessonsFinished}/{course.lessons.length}</span> lessons</span>
                </Navbar.Brand>
            </Container>
        </Navbar>

        <div style={{ height: `${height}px` || "0px" }}></div>
    </>
}

export default CourseHeader