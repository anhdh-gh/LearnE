import '../assets/css/Loader.css'
import { useSelector } from "react-redux"

const Loader = (props) => {

    const { useStateLoader } = props
    const isShow = useSelector(state =>  state.UI.Loader.isShow)

    return ((useStateLoader && isShow ) || !useStateLoader) ? 
        <div className="loader-container">
            <div className="main">
                <h1 className="title text-4xl">Learn E</h1>
                <div className="lds-ellipsis"><div></div><div></div><div></div><div></div></div>
            </div>
        </div>
    : props?.children
}

export default Loader