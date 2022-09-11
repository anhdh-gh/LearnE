package source.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * Base Business Exception Class
 * When you create a new exception, it must extend this class.
 */
@Getter
public class BusinessException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1905122041950251207L;
    private final BusinessError error;

    public BusinessException(int code, String message, HttpStatus status) {
       super(message);
       this.error = new BusinessError(code, message, status);
    }
    public BusinessException(BusinessError error) {
        super(error.getMessage());
        this.error = error;
    }

    public BusinessException(BusinessError error, String message) {
        super(message);
        this.error = error;
    }

    public BusinessException(BusinessError error, String message, Throwable cause) {
        super(message, cause);
        this.error = error;
    }
}
