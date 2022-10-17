import 'bootstrap/dist/css/bootstrap.min.css'
import "react-toastify/dist/ReactToastify.css"
import '@fortawesome/fontawesome-free/css/all.min.css'
import { Toast, Loader, ToastModal, TopLoader } from "./components"
import { useEffect } from 'react'
import { useDispatch, useSelector } from 'react-redux'
import { initInfo } from './redux/actions'
import AppNavigator from './navigation/AppNavigator'
import { NotFound } from './pages'
import _ from 'lodash'
// import { setDimensionBrowserWindow } from './redux/actions'

const App = () => {

  const dispatch = useDispatch()
  const user = useSelector(state => state.user)

  useEffect(() => {
    if (_.isEmpty(user)) {
      dispatch(initInfo())
    }
    // window.addEventListener('resize', () => dispatch(setDimensionBrowserWindow({width: window.innerWidth, height: window.innerHeight})))
    // return window.removeEventListener('resize', () => dispatch(setDimensionBrowserWindow({width: window.innerWidth, height: window.innerHeight})))

  }, [dispatch, user])

  return <div className="App select-none">
    <AppNavigator>
      <Loader useStateLoader={true}>
        <NotFound useStateNotFound={true} />
        <TopLoader />
        <Toast />
        <ToastModal />
      </Loader>
    </AppNavigator>
  </div>
}

export default App