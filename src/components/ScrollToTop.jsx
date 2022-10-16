import { useEffect } from "react"
import { useLocation } from "react-router-dom"
import { useDispatch } from "react-redux"
import { hideNotFound } from "../redux/actions"

const ScrollToTop = () => {

    const { pathname } = useLocation()
    const dispatch = useDispatch()

    useEffect(() => {
        window.scrollTo(0, 0)

        return () => dispatch(hideNotFound())
    }, [ pathname, dispatch ])

    return null
}

export default ScrollToTop