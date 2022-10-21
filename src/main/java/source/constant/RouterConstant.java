package source.constant;

public class RouterConstant {

    public final static String CREATE_USER = "user/create";

    public final static String GET_USER_BY_ID = "user/get-user-by-id";

    public final static String COMPARE_PASSWORD = "user/compare-password";

    public static final String GET_ALL_USER = "user/all";

    public static final String UPDATE_USER = "user/update";
    public static final String DELETE_USER = "user/delete";

    public static final String GET_USER_LIST_BY_IDS = "/user/get-by-ids";

    private RouterConstant() {
    }
}