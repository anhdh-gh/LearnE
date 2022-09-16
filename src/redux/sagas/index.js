import { all, call } from 'redux-saga/effects'
import userWatcher from './userSaga'

export default function* rootSaga() {
    yield all([ // Chứa các trình lắng nghe saga
        call(userWatcher)
    ])
}