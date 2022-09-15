import { combineReducers } from 'redux'
import UISlice from './features/UISlice'

export default combineReducers({
    UI: UISlice
})