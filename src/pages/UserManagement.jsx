import '../assets/css/UserManagementPage.css'
import { Header, Footer, Sider, Pagination, ModalConfirm } from "../components"
import { ROUTE_PATH, STATUS_CODES } from '../constants'
import { Button, Card, Badge, OverlayTrigger, Tooltip, Offcanvas, Form } from 'react-bootstrap'
import { SearchBox, UserInfo } from '../components'
import { useSelector } from 'react-redux'
import { History } from '../components/NavigateSetter'
import { UserApi } from '../api'
import { useQuery } from '@tanstack/react-query'
import { useParams } from 'react-router'
import { useLayoutEffect, useCallback, useState } from 'react'
import { useDispatch } from "react-redux"
import { showLoader, hideLoader, showNotFound, hideNotFound } from '../redux/actions'
import _ from 'lodash'
import AvatarIcon from '../assets/img/avatar-icon.jpg'
import { CommonUtil } from '../utils'

const UserManagement = (props) => {

    const dispatch = useDispatch()
    const { page } = useParams()
    const user = useSelector(state => state.user)
    const size = 8
    const [showVEUser, setShowVEUser] = useState({ show: false })
    const [userRemove, setUserRemove] = useState(false)

    const { data: responseGetAllUsers, isLoading: isLoadingGetAllUsers, isFetching: isFetchingGetAllUsers, isError: isErrorGetAllUsers, refetch: getAllUsers } = useQuery(
        ["getAllUsers", page],
        () => UserApi.getAllUsers(page, size),
        {
            refetchOnWindowFocus: false,
        }
    )

    useLayoutEffect(() => {
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

    const refreshPage = useCallback(() => {
        getAllUsers(page)
    }, [getAllUsers, page])

    useLayoutEffect(() => {
        refreshPage()
    }, [refreshPage])

    return !isLoadingGetAllUsers && !isFetchingGetAllUsers && !isErrorGetAllUsers && responseGetAllUsers?.meta?.code === STATUS_CODES.SUCCESS && !_.isEmpty(responseGetAllUsers?.data) && <>
        <Header />
        <Sider>
            <div className="UserManagementPage-container">
                {/* Phần các chức năng bên trên */}
                <div className="func">
                    <div className="container-xl">
                        <div className="row">
                            <div className="col-md">
                                <UserInfo limit={30} user={user} />
                            </div>
                            <div className="col-md d-flex align-items-end justify-content-between mt-4 mt-md-0">
                                <SearchBox placeholder="Search" />
                                <Button className='h-fit font-bold' onClick={() => History.push(`${ROUTE_PATH.ADMIN_USER_VIEW_ALL}/0`)}>Home</Button>
                            </div>
                        </div>
                    </div>
                </div>

                <div className="content pb-3">
                    <div className="container-xl">
                        <Pagination className="row"
                            classNamePagination={`m-0 mt-3`}
                            hrefPrev={`${ROUTE_PATH.ADMIN_USER_VIEW_ALL}/${parseInt(page) - 1}`}
                            hrefNext={`${ROUTE_PATH.ADMIN_USER_VIEW_ALL}/${parseInt(page) + 1}`}
                            hrefCurrent={`${ROUTE_PATH.ADMIN_USER_VIEW_ALL}/${parseInt(page)}`}
                            disabledPrev={responseGetAllUsers?.data?.first}
                            disabledNext={responseGetAllUsers?.data?.last}
                            onClickCurrent={refreshPage}
                            page={parseInt(page) + 1}
                            totalPages={responseGetAllUsers?.data?.totalPages}
                            hide={(responseGetAllUsers?.data && (responseGetAllUsers?.data?.totalElements <= size || _.isEmpty(responseGetAllUsers?.data?.content))) ? true : false}
                        >
                            {
                                responseGetAllUsers?.data?.content?.map(user => <div key={user?.id} className="col-sm-6 col-md-4 col-lg-3 mt-3">
                                    <Card className="card-user">
                                        <Card.Img variant="top" src={user?.avatar || AvatarIcon} onClick={() => setShowVEUser({ type: 'view', ...user, show: true })} />
                                        <Card.Body onClick={() => setShowVEUser({ type: 'view', ...user, show: true })}>
                                            <Card.Title className="title">{user?.userName}</Card.Title>
                                            <Card.Subtitle className="mb-2 text-muted">
                                                <Badge pill bg="warning" text="dark">{user?.role?.toUpperCase()}</Badge>
                                            </Card.Subtitle>
                                            <Card.Text>{user?.account?.email}</Card.Text>
                                        </Card.Body>

                                        <Card.Footer className="d-flex justify-content-between">
                                            <OverlayTrigger placement="bottom" overlay={<Tooltip>Edit</Tooltip>}>
                                                <Badge bg="primary" onClick={() => setShowVEUser({ ...user, show: true, type: 'update' })}>
                                                    <i className="fas fa-edit fs-6" />
                                                </Badge>
                                            </OverlayTrigger>

                                            <OverlayTrigger placement="bottom" overlay={<Tooltip>Remove</Tooltip>}>
                                                <Badge bg="danger" onClick={() => setUserRemove({...user})}>
                                                    <i className="fas fa-trash-alt fs-6" />
                                                </Badge>
                                            </OverlayTrigger>
                                        </Card.Footer>
                                    </Card>
                                </div>)
                            }
                        </Pagination>
                    </div>
                </div>
            </div>

            <ModalConfirm
                show={userRemove ? true : false}
                setShow={() => setUserRemove(false)}
                title="Confirm"
                message={`Are you sure you want to remove user: "${userRemove?.userName}"?`}
                handleNo={() => setUserRemove(false)}
                handleYes={() => setUserRemove(false)}
            />

            {showVEUser?.show && <Offcanvas placement="end" show={showVEUser?.show} onHide={() => setShowVEUser({ show: false })}>
                <Offcanvas.Header closeButton className="border-bottom">
                    <Offcanvas.Title>
                        {showVEUser?.type === 'update' ? 'Update user' : 'View user'}
                    </Offcanvas.Title>
                </Offcanvas.Header>
                <Offcanvas.Body>
                    <img src={showVEUser?.avatar || AvatarIcon} alt="avata" className="w-100" />
                    <table className="table table-bordered table-container h-auto">
                        <thead className="title-table align-middle">
                            <tr>
                                <th scope="col" colSpan="2">User information</th>
                            </tr>
                        </thead>
                        <tbody>
                            {showVEUser?.id && <tr>
                                <td className="fw-bold">Id</td>
                                <td>{showVEUser?.id}</td>
                            </tr>}
                            {showVEUser?.role && <tr>
                                <td className="fw-bold">Role</td>
                                <td>
                                    {showVEUser?.type === 'update'
                                        ? <Form.Select style={{ cursor: 'pointer' }} size="sm" defaultValue={showVEUser?.role}>
                                            <option value="ADMIN">ADMIN</option>
                                            <option value="USER">USER</option>
                                        </Form.Select>
                                        : showVEUser?.role
                                    }
                                </td>
                            </tr>}
                            {showVEUser?.email && <tr>
                                <td className="fw-bold">Gender</td>
                                <td>{showVEUser?.email}</td>
                            </tr>}
                            {showVEUser?.userName && <tr>
                                <td className="fw-bold">Username</td>
                                <td>{showVEUser?.userName}</td>
                            </tr>}
                            {showVEUser?.dateOfBirth && <tr>
                                <td className="fw-bold">Date of birth</td>
                                <td>{showVEUser?.dateOfBirth}</td>
                            </tr>}
                            {showVEUser?.phoneNumber && <tr>
                                <td className="fw-bold">Phone number</td>
                                <td>{showVEUser?.phoneNumber}</td>
                            </tr>}
                            {showVEUser?.account?.email && <tr>
                                <td className="fw-bold">Email</td>
                                <td>{showVEUser?.account?.email}</td>
                            </tr>}
                            {showVEUser?.address && <tr>
                                <td className="fw-bold">Address</td>
                                <td>{showVEUser?.address}</td>
                            </tr>}
                            {showVEUser?.fullName && <tr>
                                <td className="fw-bold">FullName</td>
                                <td>{showVEUser?.fullName}</td>
                            </tr>}
                            {showVEUser?.createTime && <tr>
                                <td className="fw-bold">Create time</td>
                                <td>{CommonUtil.getDateStringFromMilliseconds(showVEUser?.createTime)}</td>
                            </tr>}
                            {showVEUser?.updateTime && <tr>
                                <td className="fw-bold">Update time</td>
                                <td>{CommonUtil.getDateStringFromMilliseconds(showVEUser?.updateTime)}</td>
                            </tr>}
                        </tbody>
                    </table>
                    {showVEUser?.type === 'update' &&
                        <Button
                            // onClick={handleUpdate}
                            className="btn btn-primary w-100 fw-bold mt-3">
                            Update
                        </Button>
                    }
                </Offcanvas.Body>
            </Offcanvas>}

            <Footer />
        </Sider>
    </>
}

export default UserManagement