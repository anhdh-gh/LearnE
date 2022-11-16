import { Header, Footer, CreateEditStudySet } from "../components"
import { useParams } from 'react-router'
import { StudysetApi } from '../api'
import { useQuery } from '@tanstack/react-query'
import { useLayoutEffect  } from 'react'
import { useSelector, useDispatch } from "react-redux"
import { showLoader, hideLoader, showNotFound, hideNotFound } from '../redux/actions'
import { STATUS_CODES, ROUTE_PATH, ROLE } from '../constants'
import _ from 'lodash'
import { History } from "../components/NavigateSetter"
import { Notification } from '../utils'

const EditStudySet = (props) => {

    const { studysetId } = useParams()
    const dispatch = useDispatch()
    const user = useSelector(state => state.user)

    const { data: responseCheckOwnerStudysetValid, isLoading, isFetching, isError } = useQuery(
        ["checkOwnerStudysetValid"],
        () => user?.role === ROLE.ADMIN ? StudysetApi.getStudysetById(studysetId) : StudysetApi.checkOwnerStudysetValid(studysetId),
        {
            refetchOnWindowFocus: false,
        }
    )

    useLayoutEffect (() => {

        if (isLoading || isFetching) {
            dispatch(showLoader())
        } else {
            dispatch(hideLoader())
        }

        if (isError || (responseCheckOwnerStudysetValid?.meta && responseCheckOwnerStudysetValid?.meta?.code !== STATUS_CODES.SUCCESS)) {
            dispatch(showNotFound())
        } else {
            dispatch(hideNotFound())
        }

        return () => {
            dispatch(hideLoader())
            dispatch(hideNotFound())
        }
    }, [responseCheckOwnerStudysetValid, dispatch, isError, isFetching, isLoading])

    const handleUpdateStudyset = (studyset) => {
        studyset.id = studysetId
        dispatch(showLoader())
        StudysetApi.updateStudyset(studyset, responseCheckOwnerStudysetValid?.data?.ownerUserId)
        .then(res => {
            const { meta } = res
            if(meta.code === STATUS_CODES.SUCCESS) {
                const { data: studyset } = res
                dispatch(hideLoader()) 
                History.push(`${ROUTE_PATH.STUDY_SET_VIEW_DETAIL}/${studyset?.id}`)
                Notification.success("Updated successfully!")
            } else {
                dispatch(hideLoader())
                Notification.error('Error, try again!')
            }
        })
    } 

    return !isLoading && !isFetching && !isError && responseCheckOwnerStudysetValid?.meta?.code === STATUS_CODES.SUCCESS && !_.isEmpty(responseCheckOwnerStudysetValid?.data) && <>
        <Header/>
        <div>
            <CreateEditStudySet 
                studyset={responseCheckOwnerStudysetValid?.data} 
                handleSubmit={handleUpdateStudyset}
            />
        </div>
        <Footer/>
    </>
}

export default EditStudySet