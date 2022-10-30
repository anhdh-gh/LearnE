import '../assets/css/StudySetPage.css'
import { Header, Footer, CardStudySet, SearchBox, UserInfo, Pagination } from '../components'
import { useParams } from 'react-router'
import { StudysetApi } from '../api'
import { useEffect } from 'react'
import { useQuery } from '@tanstack/react-query'
import _ from 'lodash'
import { showLoader, hideLoader, showNotFound, hideNotFound } from '../redux/actions'
import { useSelector, useDispatch } from "react-redux"
import { Button } from 'react-bootstrap'
import { STATUS_CODES, ROUTE_PATH } from '../constants'
import { History } from '../components/NavigateSetter'

const ShowAllStudyset = (props) => {

    const { page } = useParams()
    const dispatch = useDispatch()
    const user = useSelector(state => state.user)

    const size = 9

    const { data: responseGetAllByOwnerUserId, isLoading, isFetching, isError, refetch: getAllByOwnerUserId } = useQuery(
        ["getAllByOwnerUserId", page],
        () => StudysetApi.getAll(page, size),
        {
            refetchOnWindowFocus: false,
        }
    )

    const refreshPage = () => {
        getAllByOwnerUserId(page)
    }

    useEffect(() => {
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

    return !isLoading && !isFetching && !isError && responseGetAllByOwnerUserId?.meta?.code === STATUS_CODES.SUCCESS && !_.isEmpty(responseGetAllByOwnerUserId?.data) && <>
        <Header />
        <div className="study-set-page-container">

            {/* Phần các chức năng bên trên */}
            <div className="navigation">
                <div className="container-xl">
                    <div className="row">
                        <div className="col-md">
                            <UserInfo limit={30} user={user} />
                        </div>
                        <div className="col-md d-flex align-items-end justify-content-between mt-4 mt-md-0">
                            <SearchBox placeholder="Search" />
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
                                        showHeader={true}
                                        showFooterPublic={true}
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