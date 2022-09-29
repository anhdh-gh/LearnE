package source.third_party.auth.constant;

public class RouterAuthServiceConstant {



    private RouterAuthServiceConstant() {};

    public final static String SIGN_IN = "/user/signin";

    public final static String SIGN_UP = "/user/signup";

    public static final String GET_ALL_USER = "/user/all";

    public static final String UPDATE_USER = "/user/update";

    public static final String DELETE_USER = "/user/delete";

    public static final String REFRESH_TOKEN = "/user/refreshtoken";

    public static final String GET_USER_INFORMATION = "/user/information";

    public static final String GET_USER_BY_ID = "/user/get-user-by-id";

    public static final String DELETE_USER_BY_ID = "/user/delete-user-by-id";

    public static final String QUESTION_CREATE = "/question/create";

    public static final String QUESTION_GET_ALL = "/question/all";

    public static final String QUESTION_GET_BY_QUESTION_ID = "/question/get-by-id";

    public static final String COURSE_CREATE = "/course/create";
}