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
        }
    }
})

export const { saveUser } = UserSlice.actions

export default UserSlice.reducer