import { Header, Footer } from '../components'
import { useEffect } from 'react'
import { setUrl } from '../redux/actions'
import { ROUTE_PATH } from '../constants'
import { useDispatch } from 'react-redux'

const ShowCourseDetail = (props) => {

    const dispatch = useDispatch()

    useEffect(() => {
        dispatch(setUrl(ROUTE_PATH.SHOW_COURSE_DETAIL))
    }, [ dispatch ])

    return <>
        <Header/>
        <div className="min-h-screen container-xl">
        </div>
        <Footer/>
    </>
}

export default ShowCourseDetail