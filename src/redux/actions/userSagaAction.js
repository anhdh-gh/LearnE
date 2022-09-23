import ACTION_TYPE_SAGA from "./ACTION_TYPE_SAGA"

export const refreshToken = () => ({
    type: ACTION_TYPE_SAGA.REFRESH_TOKEN
})

export const getUserInfo = () => ({
    type: ACTION_TYPE_SAGA.FETCH_USER_INFO
})

export const signIn = (username, password, rememberMe) => ({
    type: ACTION_TYPE_SAGA.SIGN_IN,
    payload: {username, password, rememberMe}
})

export const signOut= () => ({
    type: ACTION_TYPE_SAGA.SIGN_OUT,
})

export const signUp = (email, password, username)  => ({
    type: ACTION_TYPE_SAGA.SIGN_UP,
    payload: {email, password, username}
})