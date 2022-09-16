import 'bootstrap/dist/css/bootstrap.min.css'
import "react-toastify/dist/ReactToastify.css"
import '@fortawesome/fontawesome-free/css/all.min.css'
import { Toast, Loader } from "./components"
import AppNavigator from './navigation/AppNavigator'
import { useEffect } from 'react'
import { useDispatch } from 'react-redux'
import { getUserInfo } from './redux/actions/userSagaAction'

const App = () => {

  const dispatch = useDispatch()

  useEffect(() => {
    dispatch(getUserInfo())
  }, [dispatch])

  return <Loader>
    <div className="App">
      <Toast />
      <AppNavigator />
    </div>
  </Loader>
}

export default App