import '../assets/css/StudySetPage.css'
import { Header, Footer, CardStudySet, SearchBox, UserInfo, Pagination } from '../components'
import { useParams } from 'react-router'
import { StudysetApi } from '../api'
import { useLayoutEffect , useCallback } from 'react'
import { useQuery } from '@tanstack/react-query'
import _ from 'lodash'
import { showLoader, hideLoader, showNotFound, hideNotFound } from '../redux/actions'
import { useSelector, useDispatch } from "react-redux"
import { Button } from 'react-bootstrap'
import { STATUS_CODES, ROUTE_PATH } from '../constants'
import { History } from '../components/NavigateSetter'
import { useSearchParams } from 'react-router-dom'
import { useDebounce } from 'use-debounce'

const ShowAllStudyset = (props) => {

    const { page } = useParams()
    const dispatch = useDispatch()
    const user = useSelector(state => state.user)
    const [searchParams, setSearchParams] = useSearchParams()
    const [titleSearch] = useDebounce(searchParams.get('title'), 1000)

    const size = 9

    const { data: responseGetAllStudyset, isLoading, isFetching, isError, refetch: getAllStudyset } = useQuery(
        ["getAllStudyset", page],
        () => _.isEmpty(searchParams.get('title')) ? StudysetApi.getAll(page, size) : StudysetApi.searchAll(searchParams.get('title'), page, size),
        {
            refetchOnWindowFocus: false,
        }
    )

    const refreshPage = useCallback(() => {
        getAllStudyset(page)
    }, [getAllStudyset, page])

    useLayoutEffect (() => {
        refreshPage()
        // eslint-disable-next-line
    }, [ titleSearch ])

    useLayoutEffect (() => {
        if (isLoading || isFetching) {
            dispatch(showLoader())
        } else {
            dispatch(hideLoader())
        }

        if (isError || (responseGetAllStudyset?.meta && responseGetAllStudyset?.meta?.code !== STATUS_CODES.SUCCESS) || parseInt(page) < 0) {
            dispatch(showNotFound())
        } else {
            dispatch(hideNotFound())
        }

        return () => {
            dispatch(hideLoader())
            dispatch(hideNotFound())
        }
    }, [responseGetAllStudyset, dispatch, page, isError, isFetching, isLoading])

    return !isLoading && !isFetching && !isError && responseGetAllStudyset?.meta?.code === STATUS_CODES.SUCCESS && !_.isEmpty(responseGetAllStudyset?.data) && <>
        <Header />
        <div className="study-set-page-container">

            {/* Phần các chức năng bên trên */}
            <div className="navigation">
                <div className="container-xl">
                    <div className="row">
                        {(!_.isEmpty(user) || responseGetAllStudyset?.data?.content[0]?.ownerUser) && <div className="col-md">
                            <UserInfo className="cursor-pointer" limit={30} user={_.isEmpty(user) ? responseGetAllStudyset?.data?.content[0]?.ownerUser : user} onClick={() => History.push(`${ROUTE_PATH.STUDY_SET_VIEW}/${user?.id}/0`)} />
                        </div>}
                        <div className="col-md d-flex align-items-end justify-content-between mt-4 mt-md-0">
                            <SearchBox value={searchParams.get('title') || ''} placeholder="Search" onChange={e => setSearchParams(_.isEmpty(e.target.value.trim()) ? {} : { 'title': e.target.value.trim() })} />
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
                        hrefPrev={`${ROUTE_PATH.STUDY_SET_VIEW}/${parseInt(page) - 1}`}
                        hrefNext={`${ROUTE_PATH.STUDY_SET_VIEW}/${parseInt(page) + 1}`}
                        hrefCurrent={`${ROUTE_PATH.STUDY_SET_VIEW}/${parseInt(page)}`}
                        disabledPrev={responseGetAllStudyset?.data?.first}
                        disabledNext={responseGetAllStudyset?.data?.last}
                        onClickCurrent={refreshPage}
                        page={parseInt(page) + 1}
                        totalPages={responseGetAllStudyset?.data?.totalPages}
                        hide={(responseGetAllStudyset?.data && (responseGetAllStudyset?.data?.totalElements <= size || _.isEmpty(responseGetAllStudyset?.data?.content))) ? true : false}
                    >
                        {
                            responseGetAllStudyset?.data?.content?.map((studyset, index) =>
                                <div className="col-md-6 col-lg-4 mb-3" key={studyset.id}>
                                    <CardStudySet
                                        studyset={studyset}
                                        showHeader={true}
                                    />
                                </div>
                            )
                        }
                    </Pagination>
                </div>
            </div>
        </div>
        <Footer />
    </>
}

export default ShowAllStudyset