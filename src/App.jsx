import 'bootstrap/dist/css/bootstrap.min.css'
import "react-toastify/dist/ReactToastify.css"
import '@fortawesome/fontawesome-free/css/all.min.css'
import { Toast, Loader, ToastModal } from "./components"
import { useEffect } from 'react'
import { useDispatch, useSelector } from 'react-redux'
import { getUserInfo } from './redux/actions/userSagaAction'
import AppNavigator from './navigation/AppNavigator'
// import { setDimensionBrowserWindow } from './redux/actions'

const App = () => {

  // Đoạn userInfo loading này dùng nhiều chỗ, thì phải tách nó trong state ra chứ không để trong Header nữa
  const isLoadingUserInfo = useSelector(state => state.UI.Header.userInfo.isLoading)
  const dispatch = useDispatch()

  useEffect(() => {
    dispatch(getUserInfo())
    // window.addEventListener('resize', () => dispatch(setDimensionBrowserWindow({width: window.innerWidth, height: window.innerHeight})))
    // return window.removeEventListener('resize', () => dispatch(setDimensionBrowserWindow({width: window.innerWidth, height: window.innerHeight})))
  }, [ dispatch ])

  return <div className="App select-none">
    {
      isLoadingUserInfo 
      ? <Loader useStateLoader={true}/> 
      : <>
        <Toast />
        <ToastModal/>
        <AppNavigator />    
      </>
    }
  </div>
}

export default App