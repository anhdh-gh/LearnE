import '../assets/css/ShowCourseDetail.css'
import { Footer } from '../components'
import { useEffect } from 'react'
import { setUrl } from '../redux/actions'
import { ROUTE_PATH } from '../constants'
import { useDispatch } from 'react-redux'

const ShowLessonDetail = (props) => {

    const dispatch = useDispatch()

    useEffect(() => {
        dispatch(setUrl(ROUTE_PATH.SHOW_LESSON_DETAIL))
    }, [dispatch])

    return <>
        

        <div className="container-xl">

        </div>

        <Footer />
    </>
}

export default ShowLessonDetail