import '../assets/css/StudySetPage.css'
import { Header, Footer, CardStudySet, SearchBox, UserInfo, Pagination, ModalConfirm } from '../components'
import { useParams } from 'react-router'
import { StudysetApi } from '../api'
import { useState, useLayoutEffect , useCallback } from 'react'
import { useQuery } from '@tanstack/react-query'
import _ from 'lodash'
import { showLoader, hideLoader, showNotFound, hideNotFound } from '../redux/actions'
import { useSelector, useDispatch } from "react-redux"
import { Button } from 'react-bootstrap'
import { STATUS_CODES, ROUTE_PATH } from '../constants'
import { History } from '../components/NavigateSetter'
import { Notification } from '../utils'
import { useSearchParams } from 'react-router-dom'
import { useDebounce } from 'use-debounce'

const ShowListStudyset = (props) => {

    const { ownerUserId, page } = useParams()
    const dispatch = useDispatch()
    const user = useSelector(state => state.user)
    const [studysetRemove, setStudysetRemove] = useState(false)
    const [searchParams, setSearchParams] = useSearchParams()
    const [titleSearch] = useDebounce(searchParams.get('title'), 1000)

    const size = 9
    const [isOwnerStudysets, setIsOwnerStudysets] = useState()

    const { data: responseGetAllByOwnerUserId, isLoading, isFetching, isError, refetch: getAllByOwnerUserId } = useQuery(
        ["getAllByOwnerUserId", page],
        () => _.isEmpty(searchParams.get('title')) ? StudysetApi.getAllByOwnerUserId(ownerUserId, page, size) : StudysetApi.searchAllByOwnerUserId(searchParams.get('title').trim(), ownerUserId, page, size),
        {
            refetchOnWindowFocus: false,
        }
    )

    const refreshPage = useCallback(() => {
        getAllByOwnerUserId(page)
    }, [getAllByOwnerUserId, page])

    useLayoutEffect (() => {
        refreshPage()
        // eslint-disable-next-line
    }, [titleSearch])

    useLayoutEffect (() => {
        if (isLoading || isFetching) {
            dispatch(showLoader())
        } else {
            dispatch(hideLoader())
        }

        if (isError || (responseGetAllByOwnerUserId?.meta && responseGetAllByOwnerUserId?.meta?.code !== STATUS_CODES.SUCCESS) || parseInt(page) < 0) {
            dispatch(showNotFound())
        } else {
            dispatch(hideNotFound())
        }

        return () => {
            dispatch(hideLoader())
            dispatch(hideNotFound())
        }
    }, [responseGetAllByOwnerUserId, dispatch, page, isError, isFetching, isLoading])

    useLayoutEffect (() => {
        if (!isLoading && !isFetching && !isError && responseGetAllByOwnerUserId?.meta?.code === STATUS_CODES.SUCCESS && !_.isEmpty(responseGetAllByOwnerUserId?.data)) {
            if (responseGetAllByOwnerUserId?.data?.content?.every(studyset => studyset?.ownerUser?.id === user?.id)) {
                setIsOwnerStudysets(true)
            } else {
                setIsOwnerStudysets(false)
            }
        }
    }, [responseGetAllByOwnerUserId, dispatch, isError, isFetching, isLoading, user?.id])


    const handleRemoveStudyset = (studyset) => {
        setStudysetRemove(studyset)
    }

    const processRemoveStudyset = () => {
        dispatch(showLoader())
        StudysetApi.deleteById(studysetRemove?.id, studysetRemove?.ownerUserId)
            .then(response => {
                setStudysetRemove(false)
                const { meta } = response
                if (meta.code === STATUS_CODES.SUCCESS) {
                    Notification.success('Successful removal!')
                    refreshPage()
                } else {
                    dispatch(hideLoader())
                    Notification.error('Error, try again!')
                }
            })
    }

    return !isLoading && !isFetching && !isError && responseGetAllByOwnerUserId?.meta?.code === STATUS_CODES.SUCCESS && !_.isEmpty(responseGetAllByOwnerUserId?.data) && !_.isEmpty(responseGetAllByOwnerUserId?.extraData) && <>
        <Header />
        <div className="study-set-page-container">

            {/* Phần các chức năng bên trên */}
            <div className="navigation">
                <div className="container-xl">
                    <div className="row">
                        {responseGetAllByOwnerUserId?.extraData?.ownerUser && <div className="col-md">
                            <UserInfo className="cursor-pointer" limit={30} user={responseGetAllByOwnerUserId?.extraData?.ownerUser} onClick={refreshPage} />
                        </div>}
                        <div className="col-md d-flex align-items-end justify-content-between mt-4 mt-md-0">
                            <SearchBox value={searchParams.get('title') || ''} placeholder="Search" onChange={e => setSearchParams(_.isEmpty(e.target.value.trim()) ? {} : { 'title': e.target.value })} />
                            <Button onClick={() => History.push(ROUTE_PATH.STUDY_SET_CREATE)} style={{ height: 'fit-content', fontWeight: 'bold' }}>Create</Button>
                        </div>
                    </div>
                </div>
            </div>

            {/* Phần hiển thị các study set */}
            <div className="studysets py-5">
                <div className="container-xl">
                    <Pagination className="row"
                        classNamePagination={`m-0 mt-3`}
                        hrefPrev={`${ROUTE_PATH.STUDY_SET_VIEW}/${ownerUserId}/${parseInt(page) - 1}${!_.isEmpty(searchParams.get('title')) ? `?title=${searchParams.get('title')}` : ''}`}
                        hrefNext={`${ROUTE_PATH.STUDY_SET_VIEW}/${ownerUserId}/${parseInt(page) + 1}${!_.isEmpty(searchParams.get('title')) ? `?title=${searchParams.get('title')}` : ''}`}
                        hrefCurrent={`${ROUTE_PATH.STUDY_SET_VIEW}/${ownerUserId}/${parseInt(page)}${!_.isEmpty(searchParams.get('title')) ? `?title=${searchParams.get('title')}` : ''}`}
                        disabledPrev={responseGetAllByOwnerUserId?.data?.first}
                        disabledNext={responseGetAllByOwnerUserId?.data?.last}
                        onClickCurrent={refreshPage}
                        page={parseInt(page) + 1}
                        totalPages={responseGetAllByOwnerUserId?.data?.totalPages}
                        hide={(responseGetAllByOwnerUserId?.data && (responseGetAllByOwnerUserId?.data?.totalElements <= size || _.isEmpty(responseGetAllByOwnerUserId?.data?.content))) ? true : false}
                    >
                        {
                            responseGetAllByOwnerUserId?.data?.content?.map((studyset, index) =>
                                <div className="col-md-6 col-lg-4 mb-3" key={studyset.id}>
                                    <CardStudySet
                                        studyset={studyset}
                                        handleRemoveStudyset={handleRemoveStudyset}
                                        showFooter={isOwnerStudysets}
                                    />
                                </div>
                            )
                        }
                    </Pagination>
                </div>
            </div>
        </div>
        <Footer />

        <ModalConfirm
            show={studysetRemove ? true : false}
            setShow={setStudysetRemove}
            title="Confirm"
            message={`Are you sure you want to delete study set "${studysetRemove?.title}"?`}
            handleNo={() => setStudysetRemove(false)}
            handleYes={processRemoveStudyset}
        />
    </>
}

export default ShowListStudyset