import LoadingBar from 'react-top-loading-bar'
import { useSelector } from 'react-redux'

const ProgressTopLoader = (props) => {

    const { percent } = useSelector(state =>  state.UI.ProgressTopLoader)

    return percent !== 0 ? <LoadingBar progress={percent} color='rgb(132, 204, 22)'/> : <></>
}

export default ProgressTopLoader