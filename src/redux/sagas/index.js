import { all, call } from 'redux-saga/effects'
import userWatcher from './userSaga'
import courseWatcher from './courseSaga'
import commonWatcher from './commonSaga'

export default function* rootSaga() {
    yield all([ // Chứa các trình lắng nghe saga
        call(commonWatcher),
        call(userWatcher),
        call(courseWatcher),
    ])
}