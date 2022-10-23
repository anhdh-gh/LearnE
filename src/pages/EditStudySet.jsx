import { Header, Footer, CreateEditStudySet } from "../components"
import { useParams } from 'react-router'
import { StudysetApi } from '../api'
import { useQuery } from '@tanstack/react-query'
import { useEffect } from 'react'
import { useDispatch } from "react-redux"
import { showLoader, hideLoader, showNotFound, hideNotFound } from '../redux/actions'
import { STATUS_CODES, ROUTE_PATH } from '../constants'
import _ from 'lodash'
import { History } from "../components/NavigateSetter"
import { Notification } from '../utils'

const EditStudySet = (props) => {

    const { studysetId } = useParams()
    const dispatch = useDispatch()

    const { data: responseCheckOwnerStudysetValid, isLoading, isFetching, isError } = useQuery(
        ["checkOwnerStudysetValid"],
        () => StudysetApi.checkOwnerStudysetValid(studysetId),
        {
            refetchOnWindowFocus: false,
        }
    )

    useEffect(() => {

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
        StudysetApi.updateStudyset(studyset)
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