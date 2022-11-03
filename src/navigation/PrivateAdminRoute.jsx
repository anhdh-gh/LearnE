import { Navigate, Outlet } from 'react-router-dom'
import { ROUTE_PATH, ROLE } from '../constants'
import { useSelector } from "react-redux"
import _ from 'lodash'
import { Loader } from '../components'

const PrivateAdminRoute = ({ component: Component, ...rest }) => {

    const user = useSelector(state => state.user)

    const isProcessing = useSelector(state =>  state.UI.InitIsProcessing.isProcessing)

    return isProcessing 
        ? <Loader useStateLoader={true} />
        : !_.isEmpty(user) 
            ? user?.role === ROLE.ADMIN 
                ? <Outlet/> 
                : <Navigate to={ROUTE_PATH.HOME}/>
        : <Navigate to={ROUTE_PATH.SIGN_IN}/>
}

export default PrivateAdminRoute