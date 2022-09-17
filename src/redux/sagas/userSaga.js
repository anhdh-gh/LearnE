import { STATUS_CODES } from "../../constants"
import ACTION_TYPE_SAGA from "../actions/ACTION_TYPE_SAGA"
import { showLoader, showAuthSignInIsButtonSignInSpin, hideAuthSignInIsButtonSignInSpin, 
    hideLoader, saveUser, refreshToken, getUserInfo } from '../actions'
import { Notification } from '../../utils'
import { UserApi } from '../../api'
import Cookie from 'js-cookie'
import { call, put, takeLatest } from 'redux-saga/effects'

const { FETCH_USER_INFO, SIGN_IN, REFRESH_TOKEN } = ACTION_TYPE_SAGA
const { SUCCESS } = STATUS_CODES

function* signInWorker({ payload }) {
    const { username, password, rememberMe } = payload

    yield put(showAuthSignInIsButtonSignInSpin())
    try {
        const response = yield call(UserApi.handleSignIn(username, password)) // block
        const { data, meta } = response
        if(meta.code === SUCCESS) {
            const { accessToken, tokenType, refreshToken }= data
            localStorage.setItem('accessToken', accessToken)
            localStorage.setItem('tokenType', tokenType)
            if(rememberMe) {
                Cookie.set('refreshToken', refreshToken)
            }
            Notification.success("Logged in successfully")
        } else {
            const message = (meta?.errors?.length > 0 && meta?.errors[0]?.description ) || meta?.message
            Notification.error(message)
        }
    } catch (error) {
        console.log(error)
        Notification.error("Login failed")
    }
    yield put(hideAuthSignInIsButtonSignInSpin())
}

function* getUserInfoWorker() {
    yield put(showLoader())
    try {
        const response = yield call(UserApi.handleGetUserInfo()) // block
        const { data, meta } = response
        if(meta.code === SUCCESS) {
            yield put(saveUser(data))
        } else {
            yield put(refreshToken())
        }
    } catch (error) {
        console.log(error)
    }
    yield put(hideLoader())
}

function* refreshTokenWorker() {
    yield put(showLoader())
    try {
        const refreshToken = Cookie.get('refreshToken')
        const response = yield call(UserApi.handleRefreshToken(refreshToken)) // block
        const { data, meta } = response
        if(meta.code === SUCCESS) {
            const { refreshToken }= data
            Cookie.set('refreshToken', refreshToken)
            yield put(getUserInfo())
        }
    } catch (error) {
        console.log(error)
    }
    yield put(hideLoader())
}

function* userWatcher() {
    yield takeLatest(SIGN_IN, signInWorker)
    yield takeLatest(FETCH_USER_INFO, getUserInfoWorker)
    yield takeLatest(REFRESH_TOKEN, refreshTokenWorker)
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