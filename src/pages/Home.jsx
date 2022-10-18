import '../assets/css/Home.css'
import { Button } from 'react-bootstrap'
import { Header, Footer } from '../components'
import { History } from '../components/NavigateSetter'
import { ROUTE_PATH } from '../constants'

const Home = (props) => {

    return <>
        <Header notUseHeightHeader={true} />
        <div className="homePage-container">
            <div className="header">
                <div className="container-xxl py-sm-5 py-3">
                    <div className="row">
                        <div className="col-sm-7">
                            <h1 className="fw-bold">Learn it. Own it.<br />LearnE.</h1>
                            <p className="m-0">Learn everything, step by step.</p>
                        </div>
                        <div className="col-sm-5 d-sm-flex justify-content-sm-end align-items-sm-end">
                            <Button 
                                onClick={() => History.push(`${ROUTE_PATH.SHOW_COURSE_DETAIL}/746c8e3a-f39f-4360-9df2-6f396062393a`)}
                                className="fw-bold btn btn-success mt-sm-0 mt-2"
                            >Get started</Button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <Footer/>  
    </>
}

export default Home