import '../assets/css/Sider.css'
import { History } from './NavigateSetter'
import { Sidebar, Menu, MenuItem, useProSidebar } from 'react-pro-sidebar'
import { ROUTE_PATH } from '../constants'
import { useSelector } from 'react-redux'
import { useWindowSize } from '../hooks'
import { OverlayTrigger, Tooltip } from 'react-bootstrap'
import { useLocation } from "react-router-dom"

const Sider = (props) => {

    const { pathname } = useLocation()
    const headerHeight = useSelector(state => state.UI.Header.height)
    const [ widthWindow ] = useWindowSize()
    const { collapseSidebar, toggleSidebar, collapsed, toggled, broken } = useProSidebar()

    return <div className='flex'>
        <Sidebar defaultCollapsed={widthWindow >= 992 ? false : true} className='h-screen text-white' breakPoint={"sm"} backgroundColor={"#001529"} style={{ top: `${widthWindow < 576 ? headerHeight : 0}px` }}>
            <Menu className=''>
                <MenuItem className='bg-indigo-900 hover:bg-indigo-700' onClick={() => collapseSidebar()}>
                    <div className='flex justify-center '>
                        <i className={`fa-solid fa-angle-${collapsed ? 'right' : 'left'}`}></i>
                    </div>
                </MenuItem>

                <OverlayTrigger overlay={collapsed ? <Tooltip>User</Tooltip> : <></>} placement="right">
                    <MenuItem icon={<i className="fa-solid fa-user"></i>} label="Users" className={`hover:bg-indigo-700 ${pathname === ROUTE_PATH.ADMIN_USER_VIEW_ALL || pathname.includes(ROUTE_PATH.ADMIN_HOME) ? 'bg-red-900' : ''}`} onClick={() => History.push(`${ROUTE_PATH.ADMIN_USER_VIEW_ALL}\0`)}>
                        Users
                    </MenuItem>
                </OverlayTrigger>
            </Menu>
        </Sidebar>

        {broken && !toggled && <div 
            className='fixed bottom-0 px-3 py-2 bg-indigo-900 hover:bg-indigo-800 rounded-tr-lg text-white cursor-pointer' 
            onClick={toggleSidebar}
        >
            <i className={`fa-solid fa-angle-right`}></i>
        </div>}

        <main className='grow'>
            {props?.children}
        </main>
    </div>
}

export default Sider