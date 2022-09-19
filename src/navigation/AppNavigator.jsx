import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { ScrollToTop } from '../components'
import routes from './routes'
import PrivateRoute from './PrivateRoute'
import PrivateAdminRoute from './PrivateAdminRoute'
import { NavigateSetter } from '../components'

const AppNavigator = (props) => {
    return <BrowserRouter>
        <ScrollToTop/>
        <NavigateSetter/>
        {props?.children}
        <Routes>
            {/* Private admin routers */}
            {routes.privateAdminoute.map((route, index) => <PrivateAdminRoute key={index}
                path={route.path}
                element={route.element}
            />)}

            {/* Private user routers */}
            {routes.privateRoute.map((route, index) => <PrivateRoute key={index}
                path={route.path}
                element={route.element}
            />)}

            {/* Public routers */}
            {routes.publicRoute.map((route, index) => <Route key={index}
                path={route.path}
                element={route.element}
            />)}
        </Routes>
    </BrowserRouter>
}

export default AppNavigator