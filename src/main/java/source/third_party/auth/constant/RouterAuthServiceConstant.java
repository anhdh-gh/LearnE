package source.third_party.auth.constant;

public class RouterAuthServiceConstant {


    private RouterAuthServiceConstant() {
    }

    public final static String SIGN_IN = "/user/signin";

    public final static String SIGN_UP = "/user/signup";

    public static final String GET_ALL_USER = "/user/all";

    public static final String UPDATE_USER = "/user/update";

    public static final String DELETE_USER = "/user/delete";

    public static final String REFRESH_TOKEN = "/user/refreshtoken";

    public static final String GET_USER_INFORMATION = "/user/information";

    public static final String GET_USER_BY_ID = "/user/get-user-by-id";

    public static final String DELETE_USER_BY_ID = "/user/delete-user-by-id";

    public static final String COURSE_CREATE = "/course/create";

    public static final String COURSE_UPDATE = "/course/update";

    public static final String COURSE_GET_DETAIL_FOR_USER = "/course/get-course-detail-for-user";

    public static final String COURSE_GET_ALL = "/course/get/all";

    public static final String COURSE_GET_BY_ID = "/course/get-by-id";

    public static final String COURSE_UPDATE_LESSON_STATUS = "/course/update-lesson-status";

    public static final String COURSE_DELETE_BY_ID = "/course/delete-by-id";

    public static final String CREATE_STUDYSET = "/studyset/cteate";

    public static final String UPDATE_STUDYSET = "/studyset/update";

    public static final String SAVE_STUDYSET_TEST_RESULT = "/studyset/test-result/save";

    public static final String GET_STUDYSET_BY_ID = "/studyset/get-by-id";

    public static final String GET_ALL_STUDYSET_BY_OWNER_USER_ID = "/studyset/get-all-studyset-by-owner-user-id";

    public static final String GET_ALL_STUDYSET = "/studyset/get-all-studyset";

    public static final String SEARCH_ALL_STUDYSET_BY_OWNER_USER_ID = "/studyset/search-all-studyset-by-owner-user-id";

    public static final String SEARCH_ALL_STUDYSET = "/studyset/search-all-studyset";
    public static final String DELETE_STUDYSET_BY_ID = "/studyset/delete-by-id";

    public static final String GET_RANK_STUDYSET = "/studyset/rank";

    public static final String CHECK_OWNER_STUDYSET_VALID = "/studyset/check-owner-studyset-valid";

    public static final String QUESTION_GET_ALL = "/questions/all";

    public static final String QUESTION_GET_BY_IDS = "/questions/get-by-ids";

    public static final String QUESTION_GET_BY_QUESTION_ID = "/questions/get-by-id";

    public static final String QUESTION_DELETE_BY_GROUP_QUESTION_ID = "/questions/delete-by-group-id";

    public static final String QUESTION_CREATE_LIST = "/questions/create-list";

    public static final String QUESTION_GET_BY_GROUP_QUESTION_ID = "/questions/get-by-group-id";
}