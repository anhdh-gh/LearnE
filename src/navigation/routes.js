import { ROUTE_PATH } from '../constants'
import { NotFound, Auth } from '../pages'

const routes = {
    privateAdminoute: [
    ],

    privateRoute: [
    ],

    publicRoute: [
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