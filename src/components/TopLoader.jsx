import { useRef, useEffect } from 'react'
import LoadingBar from 'react-top-loading-bar'
import { useSelector, useDispatch } from 'react-redux'
import { offDisplayTopLoader } from '../redux/actions'

const TopLoader = (props) => {
    
    const dispatch = useDispatch()
    const { isShow, isDisplay } = useSelector(state =>  state.UI.TopLoader)

    const ref = useRef(null)

    useEffect(() => {
        if(isShow) {
            ref.current.continuousStart()
        } else {
            ref.current.complete()
            dispatch(offDisplayTopLoader())
        }
    }, [ isShow, dispatch ])

    return <LoadingBar color='#d30000' ref={ref} className={`${isDisplay ? '' : 'd-none'}`}/>
}

export default TopLoader