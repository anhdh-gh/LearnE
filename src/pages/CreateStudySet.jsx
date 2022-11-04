import { Header, Footer, CreateEditStudySet } from '../components'
import { StudysetApi } from '../api'
import { Notification } from '../utils'
import { useDispatch } from 'react-redux'
import { History } from '../components/NavigateSetter'
import { showLoader, hideLoader } from '../redux/actions'
import { ROUTE_PATH, STATUS_CODES } from '../constants'
import { useLayoutEffect  } from 'react'

const CreateStudySet = (props) => {

    const dispatch = useDispatch()

    useLayoutEffect (() => {
        window.scrollTo(0, 0)
    }, [])

    const handleCreateStudyset = (studyset) => {
        dispatch(showLoader())
        StudysetApi.createStudyset(studyset)
        .then(res => {
            const { meta } = res
            if(meta.code === STATUS_CODES.SUCCESS) {
                const { data: studyset } = res
                dispatch(hideLoader()) 
                History.push(`${ROUTE_PATH.STUDY_SET_VIEW_DETAIL}/${studyset?.id}`)
                Notification.success("Created successfully!")
            } else {
                dispatch(hideLoader())
                Notification.error('Error, try again!')
            }
        })
    }

    return <>
        <Header/>
        <CreateEditStudySet handleSubmit={handleCreateStudyset} />
        <Footer/>
    </>
}

export default CreateStudySet