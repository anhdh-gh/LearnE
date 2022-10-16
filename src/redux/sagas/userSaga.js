import { STATUS_CODES, KEY } from "../../constants"
import ACTION_TYPE_SAGA from "../actions/ACTION_TYPE_SAGA"
import { 
    showAuthSignInIsButtonSignInSpin, hideAuthSignInIsButtonSignInSpin, 
    saveUser, showAuthSignUpIsButtonSignUpSpin,
    hideAuthSignUpIsButtonSignUpSpin, hideAuthSignUpIsPageSignUp, showAuthSignInIsPageSignIn
} from '../actions'
import { Notification } from '../../utils'
import { UserApi } from '../../api'
import Cookie from 'js-cookie'
import { call, put, takeLatest, select } from 'redux-saga/effects'
import { History } from '../../components/NavigateSetter'
import { Validation } from "../../validation"
import { ROUTE_PATH } from "../../constants"
const { SIGN_IN, SIGN_UP } = ACTION_TYPE_SAGA
const { SUCCESS } = STATUS_CODES

function* signInWorker({ payload }) {
    yield put(showAuthSignInIsButtonSignInSpin())
    const { username, password, rememberMe } = payload
    if(Validation.signIn(username, password)) {
        try {
            const response = yield call(UserApi.handleSignIn(username, password)) // block
            const { data, meta } = response
            if(meta.code === SUCCESS) {
                const { accessToken, tokenType, refreshToken, user } = data
                localStorage.setItem(KEY.ACCESS_TOKEN, accessToken)
                localStorage.setItem(KEY.TOKEN_TYPE, tokenType)
                if(rememberMe) {
                    Cookie.set(KEY.REFRESH_TOKEN, refreshToken)
                }
                Notification.success("Logged in successfully")
                yield put(saveUser(user))
                const { previous } = yield select(state => state.UI.Url)
                const acceptUrl = [ ROUTE_PATH.HOME, `${ROUTE_PATH.SHOW_COURSE_DETAIL}/37aeb3a7-1673-49f1-b0a7-ed5b40c36515` ].some(route => route === previous)
                History.push(acceptUrl ? previous : ROUTE_PATH.HOME)
            } else {
                const message = (meta?.errors?.length > 0 && meta?.errors[0]?.description ) || meta?.message
                Notification.error(message)
            }
        } catch (error) {
            console.error(error)
            Notification.error("Login failed")
        }
    }
    yield put(hideAuthSignInIsButtonSignInSpin())
}

function* signUpWorker({ payload }) {
    yield put(showAuthSignUpIsButtonSignUpSpin())
    const { email, password, username } = payload

    if(Validation.signUp(email, password, username)) {
        try {
            const response = yield call(UserApi.handleSignUp(email, password, username))
            const { meta } = response
            if(meta.code === SUCCESS) {
                Notification.success("Sign up successfully")
                History.push(ROUTE_PATH.SIGN_IN)
                yield put(showAuthSignInIsPageSignIn())
                yield put(hideAuthSignUpIsPageSignUp())
            } else {
                const message = (meta?.errors?.length > 0 && meta?.errors[0]?.description ) || meta?.message
                Notification.error(message)
            }
        } catch (error) {
            console.error(error)
            Notification.error("Sign up failed")
        }
    }
    yield put(hideAuthSignUpIsButtonSignUpSpin())
}

function* userWatcher() {
    yield takeLatest(SIGN_IN, signInWorker)
    yield takeLatest(SIGN_UP, signUpWorker)
}

export default userWatcher

/*
    - fork là non-blocking (thực hiện song song)
        => Các hàm fork có thể thực hiện song song với nhau
    - Là trình lắng nghe các hàm thực hiện action
    - Thường sử dụng thằng này khi mà có nhiều nhiệm vụ của 1 chức năng
    + Chức năng auth => authSaga => Có fork(login), fork(logout), fork(refreshToken)
*/

/*
    - take là blobking (thực hiện tuần tự, nếu nó chưa thực hiện xong thì những câu lệnh đằng sau nó sẽ không được thực hiện)
    - Đầu vào là ACTION_TYPE cần lắng nghe
    - Lắng nghe action được dispash lên
    - Sử dụng cùng vòng lặp vô hạn while(true) để luôn lắng nghe
    - Chỉ chạy các câu lệnh sau khi action được dispash
 */

/*
    - call là blocking
    - Dùng để gọi các hàm API
    - Dùng để gọi các watcher trong rootsaga
*/

/*
    - put: Là non-blocking
    - Dùng để dispash action
*/

/*
    - delay: Là blocking
    - Dùng để delay mấy giây sau đó mới thực hiện câu lệnh phía sau
*/

/*
    - takeLatest: Là non-blocking
    - Sử dụng thay thế fork, take và vòng lặp vô hạn while(true)
    - Nếu quy trình chưa thực hiện xong mà lại nhận được dispash AcTION
      => Hủy bỏ quy trình cũ, thực hiện quy trình mới
    - Tức là khi dispash 1 action A xong nó gọi hàm B
    - Nhưng hàm B chưa thực hiện xong thì lại có 1 dispash action A
    => Nó hủy hàm B cũ và thực hiện hàm B mới
*/

/*
    - select: Là blocking
    - Dùng để lấy dữ liệu từ state trong redux store
    - const res = yield select(state => state.something)
*/

/*
    - Tại mỗi hàm worker (là các hàm thực hiện, không phải hàm lắng nghe)
    nó có thêm 1 tham số là worker({ payload }) để lấy được payload của action gửi lên
*/

/*
    - takeEvery: Là non-blocking
    - Luôn thực thi quy trình khi mà action nó lắng nghe được gửi lên
    - Ngược với takeLatest, nó luôn thực thi cả quy trình cũ và mới
*/