import { Header, Footer } from '../components'
import { useEffect } from 'react'
import { setUrl } from '../redux/actions'
import { ROUTE_PATH } from '../constants'
import { useDispatch } from 'react-redux'

const Home = (props) => {

    const dispatch = useDispatch()

    useEffect(() => {
        dispatch(setUrl(ROUTE_PATH.HOME))
    }, [ dispatch ])

    return <>
        <Header/>
        <div className="min-h-screen"></div>
        <Footer/>  
    </>
}

export default Home