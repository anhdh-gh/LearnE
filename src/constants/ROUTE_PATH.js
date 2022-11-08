const ROUTE_PATH = {
    // Private admin routers
    ADMIN_HOME: "/dashboard",
    ADMIN_USER_VIEW_ALL: "/dashboard/user/view/all",
    ADMIN_COURSE_VIEW_ALL: "/dashboard/course/view/all",

    // Private user routers
    SHOW_LESSON_DETAIL: "/course/lesson",

    STUDY_SET_CREATE: '/studyset/create',
    STUDY_SET_EDIT: '/studyset/edit',

    // Public routers
    HOME: "/",

    CONTACT: "/contact",

    SIGN_IN: "/signin",
    SIGN_UP: "/signup",

    SHOW_ALL_COURSE: "/course/show/all",

    SHOW_COURSE_DETAIL: "/course/detail",

    STUDY_SET_VIEW: '/studyset/view',
    STUDY_SET_VIEW_DETAIL: '/studyset/view/detail',
    STUDY_SET_TEST: '/studyset/test',

    // Not found
    NOT_FOUND: '*'
}

export default ROUTE_PATH