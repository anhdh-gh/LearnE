import { ApiClient } from '.'

const ROUTE_PATH = {
    GET_ALL_QUESTION: '/questions/all',
    DELETE_BY_GROUP_ID: '/questions/delete-by-group-id',
    CREATE_LIST: '/questions/create-list',
    GET_BY_GROUP_ID: '/questions/get-by-group-id'
}

const QuestionApi = {

    // handleGetCourseDetailForUser(courseId, userId) {
    //     return () => ApiClient.post(ROUTE_PATH.GET_COURSE_DETAIL_FOR_USER, {courseId, userId})
    // },

    getByGroupId(groupId) {
        return ApiClient.post(ROUTE_PATH.GET_BY_GROUP_ID, {groupId})
    },

    // updateLessonStatus(lessonId, status) {
    //     return () => ApiClient.post(ROUTE_PATH.UPDATE_LESSON_STATUS, { lessonId, status })
    // },

    getAll(page, size) {
        return ApiClient.post(ROUTE_PATH.GET_ALL_QUESTION, { page, size })
    },

    createList(question) {
        return ApiClient.post(ROUTE_PATH.CREATE_LIST, { ...question })
    },

    deleteByGroupId(groupId) {
        return ApiClient.post(ROUTE_PATH.DELETE_BY_GROUP_ID, { groupId })
    },

    // updateCourse(course) {
    //     return ApiClient.post(ROUTE_PATH.UPDATE_COURSE, { ...course })
    // },
}

export default QuestionApi