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

    return <div className='flex sider-component'>
        <Sidebar defaultCollapsed={widthWindow >= 1200 ? false : true} className='text-white' breakPoint={"sm"} backgroundColor={"#001529"} style={{height: `calc(100vh - ${broken ? 0 : headerHeight}px)`,  top: `${broken ? headerHeight : 0}px`}}>
            <Menu className=''>
                <MenuItem className='bg-indigo-900 hover:bg-indigo-700' onClick={() => collapseSidebar()}>
                    <div className='flex justify-center '>
                        <i className={`fa-solid fa-angle-${collapsed ? 'right' : 'left'}`}></i>
                    </div>
                </MenuItem>

                <OverlayTrigger overlay={collapsed ? <Tooltip>User</Tooltip> : <></>} placement="right">
                    <MenuItem icon={<i className="fa-solid fa-user"></i>} label="Users" className={`bg-inherit hover:bg-indigo-700 ${pathname.includes(ROUTE_PATH.ADMIN_USER_VIEW_ALL) ? 'bg-red-900' : ''}`} onClick={() => History.push(`${ROUTE_PATH.ADMIN_USER_VIEW_ALL}/0`)}>
                        Users
                    </MenuItem>
                </OverlayTrigger>

                <OverlayTrigger overlay={collapsed ? <Tooltip>Courses</Tooltip> : <></>} placement="right">
                    <MenuItem icon={<i className="fa-solid fa-chalkboard"></i>} label="Courses" className={`bg-inherit hover:bg-indigo-700 ${pathname.includes(ROUTE_PATH.ADMIN_COURSE_VIEW_ALL) ? 'bg-red-900' : ''}`} onClick={() => History.push(`${ROUTE_PATH.ADMIN_COURSE_VIEW_ALL}/0`)}>
                        Courses
                    </MenuItem>
                </OverlayTrigger>

                <OverlayTrigger overlay={collapsed ? <Tooltip>Questions</Tooltip> : <></>} placement="right">
                    <MenuItem icon={<i className="fa-regular fa-file-lines"></i>} label="Questions" className={`bg-inherit hover:bg-indigo-700 ${pathname.includes(ROUTE_PATH.ADMIN_QUESTION_VIEW_ALL) ? 'bg-red-900' : ''}`} onClick={() => History.push(`${ROUTE_PATH.ADMIN_QUESTION_VIEW_ALL}/0`)}>
                        Questions
                    </MenuItem>
                </OverlayTrigger>

                <OverlayTrigger overlay={collapsed ? <Tooltip>Studysets</Tooltip> : <></>} placement="right">
                    <MenuItem icon={<i className="fa-solid fa-layer-group"></i>} label="Studysets" className={`bg-inherit hover:bg-indigo-700 ${pathname.includes(ROUTE_PATH.ADMIN_STUDYSET_VIEW_ALL) ? 'bg-red-900' : ''}`} onClick={() => History.push(`${ROUTE_PATH.ADMIN_STUDYSET_VIEW_ALL}/0`)}>
                        Studysets
                    </MenuItem>
                </OverlayTrigger>

                {/* <OverlayTrigger overlay={collapsed ? <Tooltip>Courses</Tooltip> : <></>} placement="right">
                    <SubMenu className='bg-inherit' label="Courses" icon={<i className="fa-solid fa-chalkboard"></i>} onOpenChange={(open) => console.log(open)}>
                        <MenuItem label="Courses" className={`bg-indigo-900 hover:bg-indigo-700 ${pathname.includes(ROUTE_PATH.ADMIN_COURSE_VIEW_ALL) ? 'bg-red-900' : ''}`} onClick={() => History.push(`${ROUTE_PATH.ADMIN_COURSE_VIEW_ALL}/0`)}>
                            View courses
                        </MenuItem>
                    </SubMenu>                    
                </OverlayTrigger> */}
            </Menu>
        </Sidebar>

        {broken && !toggled && <div 
            className='z-10 fixed bottom-0 px-3 py-2 bg-indigo-900 hover:bg-indigo-800 rounded-tr-lg text-white cursor-pointer' 
            onClick={toggleSidebar}
        >
            <i className={`fa-solid fa-angle-right`}></i>
        </div>}

        <main className='grow overflow-auto' style={{height: `calc(100vh - ${headerHeight}px)`}}>
            {props?.children}
        </main>
    </div>
}

export default Sider