import { ApiClient } from '.'

const ROUTE_PATH = {
    QUESTION_UPLOAD: '/question/upload',
    QUESTION_DELETE_BY_GROUP_ID: '/question/delete-by-group-id'
}

const MultimediaApi = {

    questionUpload(image, audio, groupId, id) {

        const formData = new FormData()

        if(image || audio) {
            id && formData.append('id', id)
            groupId && formData.append('groupId', groupId)
            image && formData.append('image', image)
            audio && formData.append('audio', audio)
            return ApiClient.postMultipartFormData(ROUTE_PATH.QUESTION_UPLOAD, formData)     
        }
    },

    questionDeleteByGroupId(groupId) {
        return ApiClient.postMultimedia(ROUTE_PATH.QUESTION_DELETE_BY_GROUP_ID, {groupId})
    }
}

export default MultimediaApi