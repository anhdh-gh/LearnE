import '../assets/css/Header.css'
import AvatarIcon from '../assets/img/avatar-icon.jpg'
import { Navbar, Container, Nav, NavDropdown, Badge } from 'react-bootstrap'
import { ROUTE_PATH } from '../constants'
import { useNavigate, useLocation, Link } from "react-router-dom"
import { useSelector } from 'react-redux'
import _ from 'lodash'
import { UserInfo } from '../components'

const Header = (props) => {

    const navigate = useNavigate()
    const { pathname } = useLocation()
    const user = useSelector(state => state.user)

    return <Navbar collapseOnSelect expand="sm" variant="dark" fixed="top" className="header-container">
        <Container fluid>
            <Navbar.Brand
                className="fw-bold cursor-pointer"
                onClick={() => navigate(ROUTE_PATH.HOME)}
            >
                <i className="fas fa-book-reader" /> Learn EV
            </Navbar.Brand>

            <Navbar.Toggle aria-controls="navbarScroll" />

            <Navbar.Collapse id="basic-navbar-nav">
                <Nav className="my-2 my-sm-0">

                    <Nav.Link
                        className="cursor-pointer"
                        as="span"
                        href="#"
                        active="active"
                        onClick={() => navigate(ROUTE_PATH.HOME)}
                    >
                        {pathname === ROUTE_PATH.HOME
                            ? <Badge pill bg="primary">Home</Badge>
                            : 'Home'
                        }
                    </Nav.Link>
                </Nav>

                <Nav className="ms-auto my-2 my-sm-0 user-droplist">
                    {_.isEmpty(user) ?
                        <Nav className="my-2 my-sm-0">
                            <Nav.Link
                                as="span"
                                active="active"
                                href="#"
                            ><Link to={ROUTE_PATH.SIGN_IN} className="cursor-pointer no-underline font-medium"><i className="fa-solid fa-right-to-bracket"></i> Sign in</Link>
                            </Nav.Link>
                        </Nav>
                        :
                        <NavDropdown active="active" id="navbarScrollingDropdown" align="end" className="header-user-dropList"
                            title={
                                <span className="header-user-title">
                                    <img src={user?.avatar || AvatarIcon} alt='' className="d-sm-block d-none me-2" />
                                    <span>{user?.username?.length > 15
                                        ? user?.username.substr(0, 15).concat('...')
                                        : user?.username || 'Username'}
                                    </span>
                                </span>
                            }>
                            <NavDropdown.Item disabled>
                                <UserInfo user={user}/>
                            </NavDropdown.Item>
                            <NavDropdown.Divider />
                            <NavDropdown.Item href="#" as="span">
                                <div className="cursor-pointer"><i className="fas fa-sign-out-alt" /> Sign out</div>
                            </NavDropdown.Item>
                        </NavDropdown>
                    }
                </Nav>
            </Navbar.Collapse>
        </Container>
    </Navbar>
}

export default Header