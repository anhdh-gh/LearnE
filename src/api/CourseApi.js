import { ApiClient } from '../api'

const ROUTE_PATH = {
    GET_COURSE_DETAIL_FOR_USER: '/course/get-course-detail-for-user',
    UPDATE_LESSON_STATUS: "/course/update-lesson-status",
    GET_ALL: '/course/get/all'
}

const CourseApi = {

    handleGetCourseDetailForUser(courseId) {
        return () => ApiClient.post(ROUTE_PATH.GET_COURSE_DETAIL_FOR_USER, {courseId})
    },

    updateLessonStatus(lessonId, status) {
        return () => ApiClient.post(ROUTE_PATH.UPDATE_LESSON_STATUS, { lessonId, status })
    },

    getAll(page, size) {
        return ApiClient.post(ROUTE_PATH.GET_ALL, { page, size })
    }
}

export default CourseApi