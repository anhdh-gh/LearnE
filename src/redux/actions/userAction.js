import ACTION_TYPE_SAGA from "./ACTION_TYPE_SAGA"

export const signIn = (username, password, rememberMe) => ({
    type: ACTION_TYPE_SAGA.SIGN_IN,
    payload: {username, password, rememberMe}
})

export const signOut= () => ({
    type: ACTION_TYPE_SAGA.RESET_INFO_WHEN_NO_MORE_USER,
})

export const signUp = (email, password, username)  => ({
    type: ACTION_TYPE_SAGA.SIGN_UP,
    payload: {email, password, username}
})