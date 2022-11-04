import '../assets/css/Home.css'
import { Button, Carousel } from 'react-bootstrap'
import { Header, Footer, UserInfo, Pagination, CardStudySet } from '../components'
import { History } from '../components/NavigateSetter'
import { useQuery } from '@tanstack/react-query'
import _ from 'lodash'
import { showLoader, hideLoader, showNotFound, hideNotFound } from '../redux/actions'
import { useDispatch } from "react-redux"
import { STATUS_CODES, ROUTE_PATH } from '../constants'
import { UserApi, StudysetApi } from '../api'
import { useLayoutEffect , useState } from 'react'

const Home = (props) => {

    const dispatch = useDispatch()
    const [pageStudyset, setPageStudyset] = useState(0)
    const sizeStudyset = 3

    const { data: responseGetAllUsers, isLoading: isLoadingGetAllUsers, isFetching: isFetchingGetAllUsers, isError: isErrorGetAllUsers } = useQuery(
        ["getAllUsers"],
        () => UserApi.getAllUsers(0, 10),
        {
            refetchOnWindowFocus: false,
        }
    )

    const { data: responseGetAllStudyset, isLoading: isLoadingGetAllStudyset, isFetching: isFetchingGetAllStudyset, isError: isErrorGetAllStudyset, refetch: getAllStudyset } = useQuery(
        ["getAllStudyset", pageStudyset],
        () => StudysetApi.getAll(pageStudyset, sizeStudyset),
        {
            refetchOnWindowFocus: false,
        }
    )

    useLayoutEffect (() => {
        if (isLoadingGetAllUsers || isFetchingGetAllUsers || isLoadingGetAllStudyset || isFetchingGetAllStudyset) {
            dispatch(showLoader())
        } else {
            dispatch(hideLoader())
        }

        if (isErrorGetAllUsers || isErrorGetAllStudyset || (responseGetAllUsers?.meta && responseGetAllUsers?.meta?.code !== STATUS_CODES.SUCCESS)
            || (responseGetAllStudyset?.meta && responseGetAllStudyset?.meta?.code !== STATUS_CODES.SUCCESS)) {
            dispatch(showNotFound())
        } else {
            dispatch(hideNotFound())
        }

        return () => {
            dispatch(hideLoader())
            dispatch(hideNotFound())
        }
    }, [responseGetAllUsers, dispatch, isErrorGetAllUsers, isFetchingGetAllUsers, isLoadingGetAllUsers, isErrorGetAllStudyset,
        isFetchingGetAllStudyset, isLoadingGetAllStudyset, responseGetAllStudyset])

    useLayoutEffect (() => {
        getAllStudyset(pageStudyset)
    }, [ pageStudyset, getAllStudyset ])

    return !isLoadingGetAllUsers && !isFetchingGetAllUsers && !isErrorGetAllUsers && responseGetAllUsers?.meta?.code === STATUS_CODES.SUCCESS && !_.isEmpty(responseGetAllUsers?.data) &&
        !isLoadingGetAllStudyset && !isFetchingGetAllStudyset && !isErrorGetAllStudyset && responseGetAllStudyset?.meta?.code === STATUS_CODES.SUCCESS && !_.isEmpty(responseGetAllStudyset?.data) && <>
            <Header notUseHeightHeader={true} />
            <div className="homePage-container">
                <div className="header">
                    <div className="container-xxl py-sm-5 py-3">
                        <div className="row">
                            <div className="col-sm-7">
                                <h1 className="fw-bold">Learn it. Own it.<br />LearnE.</h1>
                                <p className="m-0">Learn everything, step by step.</p>
                            </div>
                            <div className="col-sm-5 d-sm-flex justify-content-sm-end align-items-sm-end">
                                <Button
                                    onClick={() => History.push(`${ROUTE_PATH.SHOW_COURSE_DETAIL}/746c8e3a-f39f-4360-9df2-6f396062393a`)}
                                    className="fw-bold btn btn-success mt-sm-0 mt-2"
                                >Get started</Button>
                            </div>
                        </div>
                    </div>
                </div>

                <div className="main">
                    <div className="container-xl">
                        {
                            responseGetAllStudyset?.data?.content?.length > 0 && <>
                                <h4 className="fw-bold mb-5 pb-3 border-bottom border-3 border-start-0 border-end-0 border-top-0 border-danger d-inline-block">Outstanding study sets</h4>
                                <Pagination className="row"
                                    classNamePagination={`m-0 mt-3`}
                                    hrefPrev={`${ROUTE_PATH.STUDY_SET_VIEW}/${parseInt(pageStudyset) - 1}`}
                                    onClickPrev={() => setPageStudyset(pageStudyset - 1)}
                                    hrefNext={`${ROUTE_PATH.STUDY_SET_VIEW}/${parseInt(pageStudyset) + 1}`}
                                    onClickNext={() => setPageStudyset(pageStudyset + 1)}
                                    hrefCurrent={`${ROUTE_PATH.STUDY_SET_VIEW}/${parseInt(pageStudyset)}`}
                                    disabledPrev={responseGetAllStudyset?.data?.first}
                                    disabledNext={responseGetAllStudyset?.data?.last}
                                    onClickCurrent={() => getAllStudyset(pageStudyset)}
                                    page={parseInt(pageStudyset) + 1}
                                    totalPages={responseGetAllStudyset?.data?.totalPages}
                                    hide={(responseGetAllStudyset?.data && (responseGetAllStudyset?.data?.totalElements <= sizeStudyset || _.isEmpty(responseGetAllStudyset?.data?.content))) ? true : false}
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
                            </>
                        }

                        {
                            responseGetAllUsers?.data?.content?.length > 0 && <>
                                <h4 className="fw-bold my-5 pb-3 border-bottom border-3 border-start-0 border-end-0 border-top-0 border-danger d-inline-block">Regular users</h4>
                                <Carousel interval={3000} variant="dark" controls={false} indicators={false}>
                                    {
                                        responseGetAllUsers?.data?.content?.map((user, index) => <Carousel.Item key={user?.id}>
                                            <div className="d-flex justify-content-center">
                                                <UserInfo user={user} />
                                            </div>
                                        </Carousel.Item>
                                        )
                                    }
                                </Carousel>
                            </>
                        }
                    </div>
                </div>
            </div>
            <Footer />
        </>
}

export default Home