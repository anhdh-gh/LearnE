package source.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * Business Error Data Structure
 */
@Getter
@AllArgsConstructor
public class BusinessError implements Serializable {
    private static final long serialVersionUID = 2405172041950251807L;
    private int code;
    private String message;
    private HttpStatus httpStatus;
}
