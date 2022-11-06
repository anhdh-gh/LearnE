import '../assets/css/UserManagementPage.css'
import { Header, Footer, Sider } from "../components"
import { ROUTE_PATH, STATUS_CODES } from '../constants'
import { Button } from 'react-bootstrap'
import { SearchBox, UserInfo } from '../components'
import { useSelector } from 'react-redux'
import { History } from '../components/NavigateSetter'
import { UserApi } from '../api'
import { useQuery } from '@tanstack/react-query'
import { useParams } from 'react-router'
import { useLayoutEffect } from 'react'
import { useDispatch } from "react-redux"
import { showLoader, hideLoader, showNotFound, hideNotFound } from '../redux/actions'

const UserManagement = (props) => {
    
    const dispatch = useDispatch()
    const { page } = useParams()
    const user = useSelector(state => state.user)
    const size = 9

    const { data: responseGetAllUsers, isLoading: isLoadingGetAllUsers, isFetching: isFetchingGetAllUsers, isError: isErrorGetAllUsers, refetch: getAllUsers } = useQuery(
        ["getAllUsers", page],
        () => UserApi.getAllUsers(page, size),
        {
            refetchOnWindowFocus: false,
        }
    )

    useLayoutEffect (() => {
        if (isLoadingGetAllUsers || isFetchingGetAllUsers) {
            dispatch(showLoader())
        } else {
            dispatch(hideLoader())
        }

        if (isErrorGetAllUsers || (responseGetAllUsers?.meta && responseGetAllUsers?.meta?.code !== STATUS_CODES.SUCCESS)) {
            dispatch(showNotFound())
        } else {
            dispatch(hideNotFound())
        }

        return () => {
            dispatch(hideLoader())
            dispatch(hideNotFound())
        }
    }, [responseGetAllUsers, dispatch, isErrorGetAllUsers, isFetchingGetAllUsers, isLoadingGetAllUsers])

    useLayoutEffect (() => {
        getAllUsers(page)
    }, [ page, getAllUsers ])

    return <>
        <Header />
        <Sider>
            <div className="UserManagementPage-container">
                {/* Phần các chức năng bên trên */}
                <div className="func">
                    <div className="container-xl">
                        <div className="row">
                            <div className="col-md">
                                <UserInfo limit={30} user={user}/>
                            </div>
                            <div className="col-md d-flex align-items-end justify-content-between mt-4 mt-md-0">
                                <SearchBox placeholder="Search" />
                                <Button className='h-fit font-bold' onClick={() => History.push(ROUTE_PATH.ADMIN_HOME)}>Home</Button>
                            </div>
                        </div>
                    </div>
                </div>

                <div className="content pb-3">
                    <div className="container-xl">

                    </div>
                </div>
            </div>
        </Sider>
        <Footer />
    </>
}

export default UserManagement