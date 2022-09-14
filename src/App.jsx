import 'bootstrap/dist/css/bootstrap.min.css'
import "react-toastify/dist/ReactToastify.css"
import '@fortawesome/fontawesome-free/css/all.min.css'
import { Toast, Loader } from "./components"
import { useState, useEffect } from 'react'
import AppNavigator from './navigation/AppNavigator'

const App = () => {

  const [loading, setLoading] = useState(true)

  useEffect(() => {
    setTimeout(() => {
      setLoading(false)
    }, 500)
  }, [])

  return loading ? <Loader /> : <div className="App">
    <Toast />
    <AppNavigator />
  </div>
}

export default App
