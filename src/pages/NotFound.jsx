import '../assets/css/NotFound.css'
import { NavLink } from "react-router-dom"
import { ROUTE_PATH } from '../constants'

const NotFound = () => {
    return (
        <div className="notFound-container">
            <div className="title fw-bold">404!</div>
            <p className='fw-bold'>This page does not exist</p>
            <NavLink to={ROUTE_PATH.HOME}>Go to homepage</NavLink>
        </div>
    )
}

export default NotFound