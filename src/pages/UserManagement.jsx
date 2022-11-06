import '../assets/css/UserManagementPage.css'
import { Header, Footer, Sider } from "../components"
import { ROUTE_PATH } from '../constants'
import { Button } from 'react-bootstrap'
import { SearchBox, UserInfo } from '../components'
import { useSelector } from 'react-redux'
import { History } from '../components/NavigateSetter'

const UserManagement = (props) => {
    
    const user = useSelector(state => state.user)

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