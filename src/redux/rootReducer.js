import { combineReducers } from 'redux'
import UISlice from './features/UISlice'
import UserSlice from './features/UserSlice'
import CourseSlice from './features/CourseSlice'

export default combineReducers({
    UI: UISlice,
    user: UserSlice,
    course: CourseSlice
})