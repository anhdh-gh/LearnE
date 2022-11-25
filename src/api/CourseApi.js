import { ApiClient } from '../api'

const ROUTE_PATH = {
    GET_COURSE_DETAIL_FOR_USER: '/course/get-course-detail-for-user',
    GET_COURSE_BY_ID: '/course/get-by-id',
    UPDATE_LESSON_STATUS: "/course/update-lesson-status",
    GET_ALL: '/course/get/all',
    CREATE_COURSE: '/course/create',
    DELETE_COURSE: '/course/delete-by-id',
    UPDATE_COURSE: '/course/update',
    COURSE_SEARCH: '/course/search'
}

const CourseApi = {

    handleGetCourseDetailForUser(courseId, userId) {
        return () => ApiClient.post(ROUTE_PATH.GET_COURSE_DETAIL_FOR_USER, {courseId, userId})
    },

    handleGetCourseById(courseId) {
        return ApiClient.post(ROUTE_PATH.GET_COURSE_BY_ID, {courseId})
    },

    search(page, size, name) {
        return ApiClient.post(ROUTE_PATH.COURSE_SEARCH, { page, size, name })
    },

    updateLessonStatus(lessonId, status) {
        return () => ApiClient.post(ROUTE_PATH.UPDATE_LESSON_STATUS, { lessonId, status })
    },

    getAll(page, size) {
        return ApiClient.post(ROUTE_PATH.GET_ALL, { page, size })
    },

    createCourse(course) {
        return ApiClient.post(ROUTE_PATH.CREATE_COURSE, { ...course })
    },

    deleteCourse(courseId) {
        return ApiClient.post(ROUTE_PATH.DELETE_COURSE, { courseId })
    },

    updateCourse(course) {
        return ApiClient.post(ROUTE_PATH.UPDATE_COURSE, { ...course })
    },
}

export default CourseApi