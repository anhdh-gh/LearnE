import '../assets/css/Header.css'
import AvatarIcon from '../assets/img/avatar-icon.jpg'
import { Navbar, Container, Nav, NavDropdown, Badge } from 'react-bootstrap'
import { ROUTE_PATH } from '../constants'
import { useLocation, Link } from "react-router-dom"
import { useSelector, useDispatch } from 'react-redux'
import _ from 'lodash'
import { UserInfo } from '../components'
import { History } from '../components/NavigateSetter'
import { useRef, useEffect } from 'react'
import { setHeightHeader, signOut } from '../redux/actions'

const Header = (props) => {

    const { pathname } = useLocation()
    const user = useSelector(state => state.user)
    const height = useSelector(state => state.UI.Header.height)
    const dispatch = useDispatch()

    const refHeader = useRef(null)

    useEffect(() => {
        dispatch(setHeightHeader(refHeader.current.clientHeight))
    }, [dispatch])

    return <>
        <Navbar ref={refHeader} collapseOnSelect expand="sm" variant="dark" fixed="top" className="header-container">
            <Container fluid>
                <Navbar.Brand
                    className="fw-bold cursor-pointer"
                    onClick={() => History.push(ROUTE_PATH.HOME)}
                >
                    <i className="fas fa-book-reader" /> Learn EV
                </Navbar.Brand>

                <Navbar.Toggle aria-controls="navbarScroll" />

                <Navbar.Collapse id="basic-navbar-nav">
                    <Nav className="my-2 my-sm-0 d-none d-sm-flex">

                        <Nav.Link
                            className="cursor-pointer"
                            as="span"
                            active="active"
                            onClick={() => History.push(ROUTE_PATH.HOME)}
                        >
                            {pathname === ROUTE_PATH.HOME
                                ? <><Badge pill bg="primary">Home</Badge></>
                                : 'Home'
                            }
                        </Nav.Link>

                        <Nav.Link
                            className="cursor-pointer"
                            as="span"
                            active="active"
                            onClick={() => History.push(`${ROUTE_PATH.SHOW_COURSE_DETAIL}/746c8e3a-f39f-4360-9df2-6f396062393a`)}
                        >
                            {pathname.includes(ROUTE_PATH.SHOW_COURSE_DETAIL)
                                ? <Badge pill bg="primary">Course</Badge>
                                : 'Course'
                            }
                        </Nav.Link>
                    </Nav>

                    <Nav className="my-2 my-sm-0 d-sm-none d-block">

                        <Nav.Link
                            className="cursor-pointer"
                            as="span"
                            active={pathname === ROUTE_PATH.HOME && `active`}
                            onClick={() => History.push(ROUTE_PATH.HOME)}
                        >Home</Nav.Link>

                        <Nav.Link
                            className="cursor-pointer"
                            as="span"
                            active={pathname.includes(ROUTE_PATH.SHOW_COURSE_DETAIL) && "active"}
                            onClick={() => History.push(`${ROUTE_PATH.SHOW_COURSE_DETAIL}/746c8e3a-f39f-4360-9df2-6f396062393a`)}
                        >Course</Nav.Link>
                    </Nav>

                    <Nav className="ms-auto my-2 my-sm-0 user-droplist">
                        {_.isEmpty(user) ?
                            <Nav className="my-2 my-sm-0">
                                <Nav.Link
                                    as="span"
                                    active="active"
                                ><Link to={ROUTE_PATH.SIGN_IN} className="cursor-pointer no-underline font-medium"><i className="fa-solid fa-right-to-bracket"></i> Sign in</Link>
                                </Nav.Link>
                            </Nav>
                            :
                            <NavDropdown onMouseEnter={() => document.getElementById('navbarScrollingDropdown').removeAttribute("href")} active="active" id="navbarScrollingDropdown" align="end" className="header-user-dropList"
                                title={
                                    <span className="header-user-title">
                                        <img src={user?.avatar || AvatarIcon} alt='' className="d-sm-block d-none me-2" style={{ "border": "1px solid #00B871" }} />
                                        <span className='d-none d-sm-block'>{user?.userName?.length > 15
                                            ? user?.userName.substring(0, 15).concat('...')
                                            : user?.userName || 'Username'}
                                        </span>

                                        <span className='d-sm-none d-block'>{user?.userName?.length > 20
                                            ? user?.userName.substring(0, 20).concat('...')
                                            : user?.userName || 'Username'}
                                        </span>
                                    </span>
                                }>
                                <NavDropdown.Item disabled>
                                    <UserInfo user={user} />
                                </NavDropdown.Item>
                                <NavDropdown.Divider />
                                <NavDropdown.Item as="span" onClick={() => dispatch(signOut())}>
                                    <div className="cursor-pointer"><i className="fas fa-sign-out-alt" /> Sign out</div>
                                </NavDropdown.Item>
                            </NavDropdown>
                        }
                    </Nav>
                </Navbar.Collapse>
            </Container>
        </Navbar>

        <div style={{ height: `${height}px` || "0px" }}></div>
    </>
}

export default Header