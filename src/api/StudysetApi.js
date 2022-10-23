import { ApiClient } from '.'

const ROUTE_PATH = {
    STUDYSET_CREATE: '/studyset/cteate',
    STUDYSET_UPDATE: '/studyset/update',
    STUDYSET_SAVE_TEST_RESULT: '/studyset/test-result/save',
    STUDYSET_GET_ALL_BY_OWNER_USER_ID: '/studyset/get-all-studyset-by-owner-user-id',
    STUDYSET_GET_ALL: '/studyset/get-all-studyset',
    STUDYSET_GET_RANK: '/studyset/rank',
    STUDYSET_GET_BY_ID: '/studyset/get-by-id',
    STUDYSET_DELETE_BY_ID: '/studyset/delete-by-id'
}

const StudysetApi = {

    // {
    //     "title": "title",
    //     "description": "description",
    //     "wordCards": [
    //         {
    //             "key": "key",
    //             "value": "value",
    //             "image": "image"
    //         }
    //     ]
    // }
    createStudyset(studyset) {
        return ApiClient.post(ROUTE_PATH.STUDYSET_CREATE, studyset)
    },

    // {
    //     "id": "a1613ffa-78bd-4eb4-a808-de40d55f0696",
    //     "title": "title2",
    //     "description": "description",
    //     "wordCards": [
    //         {
    //             "key": "key1",
    //             "value": "value1",
    //             "image": "image1"
    //         },
    //         {
    //             "key": "key2",
    //             "value": "value2",
    //             "image": "image2"
    //         }
    //     ]
    // }
    updateStudyset(studyset) {
        return ApiClient.post(ROUTE_PATH.STUDYSET_UPDATE, studyset)
    },

    saveTestResult(studysetId, completionTime, score) {
        return ApiClient.post(ROUTE_PATH.STUDYSET_SAVE_TEST_RESULT, {
            studysetId, completionTime, score
        })
    },

    getAllByOwnerUserId(ownerUserId, page, size) {
        return ApiClient.post(ROUTE_PATH.STUDYSET_GET_ALL_BY_OWNER_USER_ID, {
            ownerUserId, page, size
        })
    },

    getAll(page, size) {
        return ApiClient.post(ROUTE_PATH.STUDYSET_GET_ALL, {
            page, size
        })
    },

    getRankStudyset(studysetId, page, size) {
        return ApiClient.post(ROUTE_PATH.STUDYSET_GET_RANK, {
            studysetId, page, size
        })
    },

    getById(studysetId) {
        return ApiClient.post(ROUTE_PATH.STUDYSET_GET_BY_ID, {studysetId})
    },

    deleteById(studysetId) {
        return ApiClient.post(ROUTE_PATH.STUDYSET_DELETE_BY_ID, {studysetId})
    },
}

export default StudysetApi