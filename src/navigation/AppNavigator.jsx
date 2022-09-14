import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { NotFound } from '../pages'
import { ROUTE_PATH } from '../constants'
import { ScrollToTop } from '../components'

const AppNavigator = (props) => {
    return <BrowserRouter>
        {props?.children}
        <ScrollToTop/>
        <Routes>
            <Route path={ROUTE_PATH.NOT_FOUND} element={<NotFound/>} />
        </Routes>
    </BrowserRouter>
}

export default AppNavigator