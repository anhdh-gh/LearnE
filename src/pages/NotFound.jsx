import '../assets/css/NotFound.css'
import { NavLink } from "react-router-dom"
import { ROUTE_PATH } from '../constants'
import { useSelector } from "react-redux"

const NotFound = (props) => {

    const { useStateNotFound } = props
    const isShow = useSelector(state =>  state.UI.NotFound.isShow)

    return ((useStateNotFound && isShow ) || !useStateNotFound) ? 
        <div className="notFound-container">
            <div className="title fw-bold">404!</div>
            <p className='fw-bold'>This page does not exist</p>
            <NavLink to={ROUTE_PATH.HOME}>Go to homepage</NavLink>
        </div>
    : props?.children
}

export default NotFound