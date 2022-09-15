import { combineReducers } from 'redux'
import UISlice from './features/UISlice'
import UserSlice from './features/UserSlice'

export default combineReducers({
    UI: UISlice,
    user: UserSlice
})