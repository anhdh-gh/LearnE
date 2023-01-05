import '../assets/css/NotFound.css'
import { useLocation, NavLink } from "react-router-dom"
import { ROUTE_PATH } from '../constants'
import { useSelector } from "react-redux"

const NotFound = (props) => {

    const { pathname } = useLocation()
    const { useStateNotFound } = props
    const isShow = useSelector(state =>  state.UI.NotFound.isShow)

    return ((useStateNotFound && isShow ) || !useStateNotFound) ? 
        <div className="notFound-container">
            <div className={`title fw-bold ${pathname === ROUTE_PATH.HOME ? 'text-8xl' : ''}`}>{pathname === ROUTE_PATH.HOME ? 'OOPS!' : '404!'}</div>
            <p className='fw-bold'>{pathname === ROUTE_PATH.HOME ? 'Something went wrong' : 'This page does not exist'}</p>
            {pathname === ROUTE_PATH.HOME && <div className="text-white text-xs my-4 px-4">Maybe the server is in a sleeping state due to long time no user access. Please click the "Reload page" button to try again!</div>}
            {pathname === ROUTE_PATH.HOME
                ? <a href={ROUTE_PATH.HOME}>Reload page</a>
                : <NavLink to={ROUTE_PATH.HOME}>Go to homepage</NavLink>}
        </div>
    : props?.children
}

export default NotFound