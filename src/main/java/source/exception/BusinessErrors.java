package source.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception Dictionary
 */
public class BusinessErrors {


    /**
     * 400
     */
    public static final BusinessError INVALID_PARAMETERS = new BusinessError(400, "Invalid parameters", HttpStatus.BAD_REQUEST);

    /**
     * 403
     */
    public static final BusinessError FORBIDDEN_ERROR = new BusinessError(403, "You don not have any permissions to access this resource", HttpStatus.FORBIDDEN);

    /**
     * 500
     */
    public static final BusinessError INTERNAL_SERVER_ERROR = new BusinessError(500, "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);

    private BusinessErrors() {
    }
}