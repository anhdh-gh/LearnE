package source.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception Dictionary
 */
public class BusinessErrors {


    /**
     * 400
     */
    public static final BusinessError INVALID_PARAMETERS = new BusinessError(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), HttpStatus.BAD_REQUEST);

    /**
     * 401
     */
    public static final BusinessError UNAUTHORIZED = new BusinessError(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase(), HttpStatus.UNAUTHORIZED);

    /**
     * 403
     */
    public static final BusinessError FORBIDDEN_ERROR = new BusinessError(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase(), HttpStatus.FORBIDDEN);

    /**
     * 500
     */
    public static final BusinessError INTERNAL_SERVER_ERROR = new BusinessError(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR);

    private BusinessErrors() {
    }
}