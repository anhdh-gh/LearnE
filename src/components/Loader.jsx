import '../assets/css/Loader.css'
import { useSelector } from "react-redux"

const Loader = (props) => {

    const isShowLoader = useSelector(state =>  state.UI.isShowLoader)

    return isShowLoader ? 
        <div className="loader-container">
            <div className="main">
                <h1 className="title">Learn E</h1>
                <div className="lds-ellipsis"><div></div><div></div><div></div><div></div></div>
            </div>
        </div>
    : props?.children
}

export default Loader