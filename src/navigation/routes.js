import { ROUTE_PATH } from '../constants'
import { 
    NotFound, Auth, Home, ShowCourseDetail, 
    ShowLessonDetail, ShowListStudyset, CreateStudySet,
    EditStudySet, ViewDetailStudySet, TestStudySet, ShowAllStudyset,
    Contact, UserManagement
} from '../pages'

const routes = {
    privateAdminoute: [
        {
            path: `${ROUTE_PATH.ADMIN_HOME}`, 
            element: <UserManagement/>
        }, 
        {
            path: `${ROUTE_PATH.ADMIN_USER_VIEW_ALL}`, 
            element: <UserManagement/>
        }, 
    ],

    privateRoute: [
        {
            path: `${ROUTE_PATH.SHOW_LESSON_DETAIL}/:courseId/:lessonId`, 
            element: <ShowLessonDetail/>
        }, 

        {
            path: `${ROUTE_PATH.STUDY_SET_CREATE}`, 
            element: <CreateStudySet/>
        }, 

        {
            path: `${ROUTE_PATH.STUDY_SET_EDIT}/:studysetId`, 
            element: <EditStudySet/>
        }, 

        {
            path: `${ROUTE_PATH.STUDY_SET_TEST}/:studysetId`, 
            element: <TestStudySet/>
        }, 
    ],

    publicRoute: [
        {
            path: ROUTE_PATH.HOME, 
            element: <Home/>
        },   

        {
            path: `${ROUTE_PATH.STUDY_SET_VIEW}/:ownerUserId/:page`, 
            element: <ShowListStudyset/>
        }, 

        {
            path: `${ROUTE_PATH.STUDY_SET_VIEW}/:page`, 
            element: <ShowAllStudyset/>
        }, 

        {
            path: `${ROUTE_PATH.STUDY_SET_VIEW_DETAIL}/:studysetId`, 
            element: <ViewDetailStudySet/>
        }, 

        {
            path: `${ROUTE_PATH.SHOW_COURSE_DETAIL}/:courseId`, 
            element: <ShowCourseDetail/>
        },     

        {
            path: ROUTE_PATH.SIGN_UP, 
            element: <Auth isSignUp={true}/>
        },   

        {
            path: ROUTE_PATH.CONTACT, 
            element: <Contact/>
        },   

        {
            path: ROUTE_PATH.SIGN_IN, 
            element: <Auth isSignIn={true}/>
        },   

        {
            path: ROUTE_PATH.NOT_FOUND, // Không match với path nào ở phía trên (luôn đặt ở cuối cùng)
            element: <NotFound />
        }
    ]
}

export default routes