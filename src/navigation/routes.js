import { ROUTE_PATH } from '../constants'
import { NotFound, Auth, Home, ShowCourseDetail, ShowLessonDetail } from '../pages'

const routes = {
    privateAdminoute: [
    ],

    privateRoute: [
    ],

    publicRoute: [
        {
            path: ROUTE_PATH.HOME, 
            element: <Home/>
        },   
        {
            path: `${ROUTE_PATH.SHOW_COURSE_DETAIL}/:courseId`, 
            element: <ShowCourseDetail/>
        },   
        {
            path: `${ROUTE_PATH.SHOW_LESSON_DETAIL}/:courseId/:lessonId`, 
            element: <ShowLessonDetail/>
        },   
        {
            path: ROUTE_PATH.SIGN_UP, 
            element: <Auth isSignUp={true}/>
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