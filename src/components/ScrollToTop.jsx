import { useLayoutEffect  } from "react"
import { useLocation } from "react-router-dom"
import { useDispatch } from "react-redux"
import { setUrl, hideNotFound } from "../redux/actions"

const ScrollToTop = () => {

    const { pathname } = useLocation()
    const dispatch = useDispatch()

    useLayoutEffect (() => {
        window.scrollTo(0, 0)

        dispatch(setUrl(pathname))
        
        return () => dispatch(hideNotFound())
    }, [ pathname, dispatch ])

    return null
}

export default ScrollToTop