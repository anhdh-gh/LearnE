import { Navigate, Outlet } from 'react-router-dom'
import { ROUTE_PATH } from '../constants'
import { useSelector } from "react-redux"
import _ from 'lodash'
import { Loader } from '../components'

// https://stackoverflow.com/questions/69864165/error-privateroute-is-not-a-route-component-all-component-children-of-rou
const PrivateRoute = ({ component: Component, ...rest }) => {

    const user = useSelector(state => state.user)

    const isProcessing = useSelector(state =>  state.UI.InitIsProcessing.isProcessing)

    return isProcessing ? <Loader useStateLoader={true} />
    : !_.isEmpty(user) ? <Outlet/> : <Navigate to={ROUTE_PATH.SIGN_IN}/>
}

export default PrivateRoute