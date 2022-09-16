import ACTION_TYPE_SAGA from "./ACTION_TYPE_SAGA"

export const refreshToken = () => ({
    type: ACTION_TYPE_SAGA.REFRESH_TOKEN
})

export const getUserInfo = () => ({
    type: ACTION_TYPE_SAGA.FETCH_USER_INFO
})

export const signIn = (username, password) => ({
    type: ACTION_TYPE_SAGA.SIGN_IN,
    payload: {username, password}
})