import { Navigate, Route } from 'react-router-dom'
import { ROUTE_PATH } from '../constants'
import { useSelector } from "react-redux"
import _ from 'lodash'

const PrivateRoute = ({ component: Component, ...rest }) => {

    const user = useSelector(state => state.user)

    return (
        <Route
            {...rest}
            render={(props) => !_.isEmpty(user) ?
                <Component {...props} /> : 
                <Navigate to={ROUTE_PATH.SIGN_IN}/>
            }
        />
    )
}

export default PrivateRoute