import { ROUTE_PATH } from '../constants'
import { NotFound } from '../pages'

const routes = {
    privateAdminoute: [
    ],

    privateRoute: [
    ],

    publicRoute: [
        {
            path: ROUTE_PATH.NOT_FOUND, // Không match với path nào ở phía trên (luôn đặt ở cuối cùng)
            element: <NotFound />
        },    
    ]
}

export default routes