import { createSlice } from '@reduxjs/toolkit'

const CourseSlice = createSlice({
    name: 'course',
    initialState: {},
    reducers: {
        
        saveCourse: (state, { payload }) => {
            return {
                ...state,
                ...payload
            }
        },

        removeCourse: (state, { payload }) => {
            return {}
        }
    }
})

export const { saveCourse, removeCourse } = CourseSlice.actions

export default CourseSlice.reducer