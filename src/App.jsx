import 'bootstrap/dist/css/bootstrap.min.css'
import "react-toastify/dist/ReactToastify.css"
import '@fortawesome/fontawesome-free/css/all.min.css'
import { Toast, Loader, ToastModal } from "./components"
import { useEffect } from 'react'
import { useDispatch } from 'react-redux'
import { getUserInfo } from './redux/actions/userSagaAction'
import AppNavigator from './navigation/AppNavigator'

const App = () => {

  const dispatch = useDispatch()

  useEffect(() => {
    dispatch(getUserInfo())
  }, [dispatch])

  return <Loader>
    <div className="App">
      <Toast />
      <ToastModal/>
      <AppNavigator />
    </div>
  </Loader>
}

export default App