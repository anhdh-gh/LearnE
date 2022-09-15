import 'bootstrap/dist/css/bootstrap.min.css'
import "react-toastify/dist/ReactToastify.css"
import '@fortawesome/fontawesome-free/css/all.min.css'
import { Toast, Loader } from "./components"
import AppNavigator from './navigation/AppNavigator'
// import { useDispatch } from 'react-redux'
// import { showLoader, hideLoader } from './redux/actions'
// import { useEffect } from 'react'

const App = () => {

  // const dispatch = useDispatch()

  // useEffect(() => {
  //   dispatch(showLoader())
  //   setTimeout(() => {
  //     dispatch(hideLoader())
  //   }, 500)
  // }, [dispatch])

  return <Loader>
    <div className="App">
      <Toast />
      <AppNavigator />
    </div>    
  </Loader>
}

export default App