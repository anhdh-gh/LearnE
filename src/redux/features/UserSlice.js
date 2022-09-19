import { createSlice } from '@reduxjs/toolkit'

const UserSlice = createSlice({
    name: 'user',
    initialState: {},
    reducers: {
        
        saveUser: (state, { payload }) => {
            return {
                ...state,
                ...payload
            }
        },

        removeUser: (state, { payload }) => {
            return {}
        }
    }
})

export const { saveUser, removeUser } = UserSlice.actions

export default UserSlice.reducer