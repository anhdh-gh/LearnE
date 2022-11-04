import { useRef, useLayoutEffect  } from 'react'
import LoadingBar from 'react-top-loading-bar'
import { useSelector, useDispatch } from 'react-redux'
import { offDisplayTopLoader } from '../redux/actions'

const TopLoader = (props) => {
    
    const dispatch = useDispatch()
    const { isShow, isDisplay } = useSelector(state =>  state.UI.TopLoader)

    const ref = useRef(null)

    useLayoutEffect (() => {
        if(isShow) {
            ref.current.continuousStart()
        } else {
            ref.current.complete()
            dispatch(offDisplayTopLoader())
        }
    }, [ isShow, dispatch ])

    return <LoadingBar color='rgb(132, 204, 22)' ref={ref} className={`${isDisplay ? '' : 'd-none'}`}/>
}

export default TopLoader