const ACTION_TYPE_SAGA = {
    // For common
    INIT_INFO: 'INIT_INFO',
    RESET_INFO_WHEN_NO_MORE_USER: 'RESET_INFO_WHEN_NO_MORE_USER',

    // For only user
    SIGN_IN: 'SIGN_IN',
    SIGN_UP: 'SIGN_UP',

    // For only for course
    GET_COURSE_BY_ID: 'GET_COURSE_BY_ID',
    UPDATE_LESSON_STATUS: 'UPDATE_LESSON_STATUS',
}
export default ACTION_TYPE_SAGA