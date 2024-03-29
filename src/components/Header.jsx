import '../assets/css/Header.css'
import AvatarIcon from '../assets/img/avatar-icon.jpg'
import { Navbar, Container, Nav, NavDropdown, Badge } from 'react-bootstrap'
import { ROUTE_PATH, ROLE } from '../constants'
import { useLocation, Link } from "react-router-dom"
import { useSelector, useDispatch } from 'react-redux'
import _ from 'lodash'
import { UserInfo } from '../components'
import { History } from '../components/NavigateSetter'
import { useRef, useLayoutEffect } from 'react'
import { setHeightHeader, signOut } from '../redux/actions'

const Header = (props) => {

    const { notUseHeightHeader } = props
    const { pathname } = useLocation()
    const user = useSelector(state => state.user)
    const height = useSelector(state => state.UI.Header.height)
    const dispatch = useDispatch()

    const refHeader = useRef(null)

    useLayoutEffect(() => {
        dispatch(setHeightHeader(refHeader.current.clientHeight))
    }, [dispatch])

    return <>
        <Navbar ref={refHeader} collapseOnSelect expand="md" variant="dark" fixed="top" className="header-container">
            <Container fluid>
                <Navbar.Brand
                    className="fw-bold cursor-pointer"
                    onClick={() => History.push(ROUTE_PATH.HOME)}
                >
                    <i className="fas fa-book-reader" /> Learn E
                </Navbar.Brand>

                <Navbar.Toggle aria-controls="navbarScroll" />

                <Navbar.Collapse id="basic-navbar-nav">
                    <Nav className="my-2 my-md-0 d-none d-md-flex">

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

                        <Nav className="my-2 my-md-0 user-droplist">
                            <NavDropdown onMouseEnter={() => document.getElementById('navbarScrollingDropdown-course').removeAttribute("href")} id="navbarScrollingDropdown-course" align="start" className="header-user-dropList"
                                title={<span>
                                    {(pathname.includes('/course') || pathname === ROUTE_PATH.TRANSLATION) && !pathname.includes('/dashboard')
                                        ? <Badge pill bg="primary">Course</Badge>
                                        : 'Course'
                                    }</span>}
                            >
                                <NavDropdown.Item as="span" onClick={() => History.push(`${ROUTE_PATH.SHOW_ALL_COURSE}/0`)}>
                                    <div className="cursor-pointer"><i className="fa-solid fa-graduation-cap"></i> Courses</div>
                                </NavDropdown.Item>
                                <NavDropdown.Divider />
                                <NavDropdown.Item as="span" onClick={() => History.push(`${ROUTE_PATH.SHOW_ALL_QUESTION}/0`)}>
                                    <div className="cursor-pointer"><i className="fa-regular fa-file-lines"></i> Questions</div>
                                </NavDropdown.Item>
                                <NavDropdown.Divider />
                                <NavDropdown.Item as="span" onClick={() => History.push(`${ROUTE_PATH.TRANSLATION}`)}>
                                    <div className="cursor-pointer"><i className="fa-solid fa-language"></i> Translation</div>
                                </NavDropdown.Item>
                            </NavDropdown>
                        </Nav>

                        <Nav className="my-2 my-md-0 user-droplist">
                            <NavDropdown onMouseEnter={() => document.getElementById('navbarScrollingDropdown-studyset').removeAttribute("href")} id="navbarScrollingDropdown-studyset" align="start" className="header-user-dropList"
                                title={<span>
                                    {pathname.includes('/studyset/') && !pathname.includes('/dashboard')
                                        ? <Badge pill bg="primary">Study set</Badge>
                                        : 'Study set'
                                    }</span>}
                            >
                                <NavDropdown.Item as="span" onClick={() => History.push(ROUTE_PATH.STUDY_SET_CREATE)}>
                                    <div className="cursor-pointer"><i className="fa-solid fa-plus"></i> Create study set</div>
                                </NavDropdown.Item>
                                <NavDropdown.Divider />
                                <NavDropdown.Item as="span" onClick={() => History.push(`${ROUTE_PATH.STUDY_SET_VIEW}/0`)}>
                                    <div className="cursor-pointer"><i className="fa-solid fa-layer-group"></i> View study sets</div>
                                </NavDropdown.Item>

                                {!_.isEmpty(user) && <><NavDropdown.Divider /><NavDropdown.Item as="span" onClick={() => History.push(`${ROUTE_PATH.STUDY_SET_VIEW}/${user?.id}/0`)}>
                                    <div className="cursor-pointer"><i className="fa-solid fa-folder"></i> My study sets</div>
                                </NavDropdown.Item></>}
                            </NavDropdown>
                        </Nav>

                        <Nav.Link
                            className="cursor-pointer"
                            as="a"
                            href={ROUTE_PATH.CONTACT}
                            active="active"
                        >
                            {pathname === ROUTE_PATH.CONTACT
                                ? <><Badge pill bg="primary">Contact</Badge></>
                                : 'Contact'
                            }
                        </Nav.Link>

                        {!_.isEmpty(user) && user?.role === ROLE.ADMIN && <Nav.Link
                            className="cursor-pointer"
                            as="a"
                            active="active"
                            onClick={() => History.push(`${ROUTE_PATH.ADMIN_USER_VIEW_ALL}/0`)}
                        >
                            {pathname.includes('/dashboard')
                                ? <><Badge pill bg="primary">Dashboard</Badge></>
                                : 'Dashboard'
                            }
                        </Nav.Link>}
                    </Nav>

                    <Nav className="my-2 my-md-0 d-md-none d-block">

                        <Nav.Link
                            className={`cursor-pointer py-0 my-3 ${pathname === ROUTE_PATH.HOME && 'font-bold'}`}
                            as="span"
                            active={`active`}
                            onClick={() => History.push(ROUTE_PATH.HOME)}
                        >Home</Nav.Link>

                        <Nav className="my-3 my-md-0 user-droplist">
                            <NavDropdown onMouseEnter={() => document.getElementById('navbarScrollingDropdown-course').removeAttribute("href")} id="navbarScrollingDropdown-course" align="start" className="header-user-dropList"
                                title={<span className={`${(pathname.includes('/course/') || pathname === ROUTE_PATH.TRANSLATION) && !pathname.includes('/dashboard') && 'header-user-title'}`}>
                                    Course</span>}
                            >
                                <NavDropdown.Item as="span" onClick={() => History.push(`${ROUTE_PATH.SHOW_ALL_COURSE}/0`)}>
                                    <div className="cursor-pointer"><i className="fa-solid fa-graduation-cap"></i> Courses</div>
                                </NavDropdown.Item>
                                <NavDropdown.Divider />
                                <NavDropdown.Item as="span" onClick={() => History.push(`${ROUTE_PATH.SHOW_ALL_QUESTION}/0`)}>
                                    <div className="cursor-pointer"><i className="fa-regular fa-file-lines"></i> Questions</div>
                                </NavDropdown.Item>
                                <NavDropdown.Divider />
                                <NavDropdown.Item as="span" onClick={() => History.push(`${ROUTE_PATH.TRANSLATION}`)}>
                                    <div className="cursor-pointer"><i className="fa-solid fa-language"></i> Translation</div>
                                </NavDropdown.Item>
                            </NavDropdown>
                        </Nav>

                        <Nav className="my-3 my-md-0 user-droplist">
                            <NavDropdown onMouseEnter={() => document.getElementById('navbarScrollingDropdown-studyset-md').removeAttribute("href")} id="navbarScrollingDropdown-studyset-md" align="start" className="header-user-dropList"
                                title={<span className={`${pathname.includes('/studyset/') && !pathname.includes('/dashboard') && 'header-user-title'}`}>Study set</span>}
                            >
                                <NavDropdown.Item as="span" onClick={() => History.push(ROUTE_PATH.STUDY_SET_CREATE)}>
                                    <div className="cursor-pointer"><i className="fa-solid fa-plus"></i> Create study set</div>
                                </NavDropdown.Item>
                                <NavDropdown.Divider />
                                <NavDropdown.Item as="span" onClick={() => History.push(`${ROUTE_PATH.STUDY_SET_VIEW}/0`)}>
                                    <div className="cursor-pointer"><i className="fa-solid fa-layer-group"></i> View study sets</div>
                                </NavDropdown.Item>
                                {!_.isEmpty(user) && <><NavDropdown.Divider /><NavDropdown.Item as="span" onClick={() => History.push(`${ROUTE_PATH.STUDY_SET_VIEW}/${user?.id}/0`)}>
                                    <div className="cursor-pointer"><i className="fa-solid fa-folder"></i> My study sets</div>
                                </NavDropdown.Item></>}
                            </NavDropdown>
                        </Nav>

                        <Nav.Link
                            className={`cursor-pointer py-0 my-3 ${pathname === ROUTE_PATH.CONTACT && 'font-bold'}`}
                            as="a"
                            active={`active`}
                            href={ROUTE_PATH.CONTACT}
                        >Contact</Nav.Link>

                        {!_.isEmpty(user) && user?.role === ROLE.ADMIN && <Nav.Link
                            className={`cursor-pointer py-0 my-3 ${pathname.includes('/dashboard') && 'font-bold'}`}
                            as="span"
                            active={`active`}
                            onClick={() => History.push(`${ROUTE_PATH.ADMIN_USER_VIEW_ALL}/0`)}
                        >Dashboard</Nav.Link>}
                    </Nav>

                    <Nav className="ms-auto my-3 my-md-0 user-droplist">
                        {_.isEmpty(user) ?
                            <Nav className="my-2 my-md-0">
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
                                        <img src={user?.avatar || AvatarIcon} alt='' className="d-md-block d-none me-2" style={{ "border": "1px solid #00B871" }} />
                                        <span className='d-none d-md-block'>{user?.userName?.length > 15
                                            ? user?.userName.substring(0, 15).concat('...')
                                            : user?.userName || 'Username'}
                                        </span>

                                        <span className='d-md-none d-block'>{user?.userName?.length > 20
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

        {!notUseHeightHeader && <div style={{ height: `${height}px` || "0px" }}></div>}
    </>
}

export default Header