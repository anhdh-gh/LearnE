import ACTION_TYPE_SAGA from "./ACTION_TYPE_SAGA"

export const getCourseById = (courseId) => ({
    type: ACTION_TYPE_SAGA.GET_COURSE_BY_ID,
    payload: {courseId}
})

export const updateLessonStatus = (lessonId, status) => ({
    type: ACTION_TYPE_SAGA.UPDATE_LESSON_STATUS,
    payload: {lessonId, status}
})