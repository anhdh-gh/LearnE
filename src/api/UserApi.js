import ApiClient from './ApiClient'

const ROUTE_PATH = {
    SIGN_IN: '/user/signin',
    GET_ALL_USERS: '/user/all',
    GET_USER_INFO: "/user/information",
    REFRESH_TOKEN: "/user/refreshtoken",
    SIGN_UP: '/user/signup',
    GET_COURSE_DETAIL_FOR_USER: '/course/get-course-detail-for-user',
    UPDATE: '/user/update',
}

const UserApi = {
    
    handleSignIn: (email, password) => {
        return () => ApiClient.post(ROUTE_PATH.SIGN_IN, {email, password})
    },

    handleGetUserInfo: () => {
        return () => ApiClient.post(ROUTE_PATH.GET_USER_INFO, {})
    },

    handleRefreshToken: (refreshToken) => {
        return () => ApiClient.post(ROUTE_PATH.REFRESH_TOKEN, {refreshToken})
    },
    
    handleSignUp: (email, password, username) => {
        return () => ApiClient.post(ROUTE_PATH.SIGN_UP, {email, password, username})
    },

    updateUser: (id, role) => {
        return ApiClient.post(ROUTE_PATH.UPDATE, {id, role})
    },

    getAllUsers: (page, size) => {
        return ApiClient.post(ROUTE_PATH.GET_ALL_USERS, {page, size})
    }
}

export default UserApi