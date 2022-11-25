import { ApiClient } from '.'

const ROUTE_PATH = {
    QUESTION_CREATE: '/question/create',
    QUESTION_UPDATE: '/question/update',
    QUESTION_DELETE_BY_ID: '/question/delete-by-id',
    QUESTION_GET_BY_ID: '/question/get-by-id',
    QUESTION_GET_ALL: '/question/get-all',
    QUESTION_SAVE_TEST_RESULT: '/question/save-test-result',
    QUESTION_GET_RANK: '/question/get-rank',
    QUESTION_SEARCH: '/question/search'
}

const QuestionApi = {

    getAll(page, size) {
        return ApiClient.post(ROUTE_PATH.QUESTION_GET_ALL, { page, size })
    },

    search(text, page, size) {
        return ApiClient.post(ROUTE_PATH.QUESTION_SEARCH, { page, size, text })
    },

    create(question) {
        return ApiClient.post(ROUTE_PATH.QUESTION_CREATE, { ...question })
    },

    update(question) {
        return ApiClient.post(ROUTE_PATH.QUESTION_UPDATE, { ...question })
    },

    deleteById(questionId) {
        return ApiClient.post(ROUTE_PATH.QUESTION_DELETE_BY_ID, { questionId })
    },

    getById(questionId) {
        return ApiClient.post(ROUTE_PATH.QUESTION_GET_BY_ID, { questionId })
    },

    saveTestResult(questionId, completionTime, score) {
        return ApiClient.post(ROUTE_PATH.QUESTION_SAVE_TEST_RESULT, { questionId, completionTime, score })
    },

    getRank(questionId, page, size) {
        return ApiClient.post(ROUTE_PATH.QUESTION_GET_RANK, { questionId, page, size })
    }
}

export default QuestionApi