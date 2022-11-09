import { ApiClient } from '../api'

const ROUTE_PATH = {
    GET_COURSE_DETAIL_FOR_USER: '/course/get-course-detail-for-user',
    GET_COURSE_BY_ID: '/course/get-by-id',
    UPDATE_LESSON_STATUS: "/course/update-lesson-status",
    GET_ALL: '/course/get/all'
}

const CourseApi = {

    handleGetCourseDetailForUser(courseId, userId) {
        return () => ApiClient.post(ROUTE_PATH.GET_COURSE_DETAIL_FOR_USER, {courseId, userId})
    },

    handleGetCourseById(courseId) {
        return ApiClient.post(ROUTE_PATH.GET_COURSE_BY_ID, {courseId})
    },

    updateLessonStatus(lessonId, status) {
        return () => ApiClient.post(ROUTE_PATH.UPDATE_LESSON_STATUS, { lessonId, status })
    },

    getAll(page, size) {
        return ApiClient.post(ROUTE_PATH.GET_ALL, { page, size })
    }
}

export default CourseApi