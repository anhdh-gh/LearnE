package source.constant;

public class RouterConstant {

    public final static String SIGN_IN = "/user/signin";

    public final static String SIGN_UP = "/user/signup";

    public static final String GET_ALL_USER = "/user/all";

    public static final String UPDATE_USER = "/user/update";

    public static final String DELETE_USER = "/user/delete";
    public static final String REFRESH_TOKEN = "/user/refreshtoken";
    public static final String USER_GET_INFO = "/user/information";
    public static final String USER_GET_BY_ID = "/user/get-user-by-id";
    public static final String ADMIN_DELETE_USER = "/user/delete-user-by-id";
    public static final String QUESTION_CREATE = "/question/create";
    public static final String QUESTION_GET_ALL = "/question/all";
    public static final String QUESTION_GET_BY_QUESTION_ID = "/question/get-by-id";

    public static final String COURSE_CREATE = "/course/create";

    public static final String QUESTION_GET_BY_IDS = "/question/get-by-ids";

    public static final String COURSE_GET_DETAIL_FOR_USER = "/course/get-course-detail-for-user";

    public static final String COURSE_UPDATE_LESSON_STATUS = "/course/update-lesson-status";

    public static final String CREATE_STUDYSET = "/studyset/cteate";

    public static final String ADD_OR_UPDATE_EXAMINATION = "/studyset/examination/add-or-update";

    public static final String GET_STUDYSET_BY_ID = "/studyset/get-by-id";
    private RouterConstant() {}
}