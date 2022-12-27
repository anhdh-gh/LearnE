import { STATUS_CODES, KEY } from "../../constants"
import ACTION_TYPE_SAGA from "../actions/ACTION_TYPE_SAGA"
import { 
    showLoader, hideLoader, 
    saveUser, removeUser, 
    removeCourse, initIsProcessingDone
} from '../actions'
import { UserApi } from '../../api'
import Cookie from 'js-cookie'
import { call, put, takeLatest } from 'redux-saga/effects'

const { INIT_INFO, RESET_INFO_WHEN_NO_MORE_USER } = ACTION_TYPE_SAGA
const { SUCCESS } = STATUS_CODES

function* initInfoWorker() {
    yield put(showLoader())
    try {
        const accessToken = localStorage.getItem(KEY.ACCESS_TOKEN)
        const tokenType = localStorage.getItem(KEY.TOKEN_TYPE)
        const refreshToken = Cookie.get(KEY.REFRESH_TOKEN)

        if ((accessToken && tokenType) || refreshToken) {
            const responseUser = yield call(UserApi.handleGetUserInfo())
            if(responseUser) {
                // Save user info
                const { meta: metaUser } = responseUser
                if(metaUser.code === SUCCESS) {
                    const { data: dataUser } = responseUser
                    yield put(saveUser(dataUser))
                }                
            }
        }
    } catch (error) {
        console.error(error)
    }
    yield put(initIsProcessingDone())
    yield put(hideLoader())
}

function* resetInfoWhenNoMoreUserWorker() {
    yield put(showLoader())
    try {
        Cookie.remove(KEY.REFRESH_TOKEN)
        localStorage.removeItem(KEY.ACCESS_TOKEN)
        localStorage.removeItem(KEY.TOKEN_TYPE)
        yield put(removeUser())
        yield put(removeCourse())
    } catch (error) {
        console.error(error)
    }
    yield put(hideLoader())
}

function* commonWatcher() {
    yield takeLatest(INIT_INFO, initInfoWorker)
    yield takeLatest(RESET_INFO_WHEN_NO_MORE_USER, resetInfoWhenNoMoreUserWorker)
}

export default commonWatcher