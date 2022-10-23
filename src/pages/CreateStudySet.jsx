import { Header, Footer, CreateEditStudySet } from '../components'
import { StudysetApi } from '../api'
import { Notification } from '../utils'
import { useDispatch } from 'react-redux'
import { History } from '../components/NavigateSetter'
import { showLoader, hideLoader } from '../redux/actions'
import { ROUTE_PATH, STATUS_CODES } from '../constants'

const CreateStudySet = (props) => {

    const dispatch = useDispatch()

    const handleCreateStudyset = (studyset) => {
        dispatch(showLoader())
        StudysetApi.createStudyset(studyset)
        .then(res => {
            const { data, meta } = res
            if(meta.code === STATUS_CODES.SUCCESS) {
                dispatch(hideLoader()) 
                // Đoạn này phải trả về trang show studyset detail
                History.push(`${ROUTE_PATH.STUDY_SET_VIEW}/${data?.ownerUserId}/0`)
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