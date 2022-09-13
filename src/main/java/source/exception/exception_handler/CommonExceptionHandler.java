package source.exception.exception_handler;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.CaseFormat;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import source.dto.response.BaseResponse;
import source.dto.response.FieldViolation;
import source.exception.BusinessError;
import source.exception.BusinessErrors;
import source.exception.BusinessException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Global exception handler for Rest API
 */
@Controller
@ControllerAdvice
@AllArgsConstructor
public class CommonExceptionHandler {

    private CommonExceptionHandler(){}

    private ObjectMapper objectMapper;
    @Autowired
    private Environment environment;

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<BaseResponse<Void>> handleBusinessException(BusinessException exception, HttpServletRequest request) {
        BaseResponse<Void> data = BaseResponse.ofFailed((String) request.getAttribute("request_id"), exception);
        return new ResponseEntity<>(data, exception.getError().getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse<Void>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, HttpServletRequest request) {
        List<FieldViolation> errors = exception.getBindingResult().getFieldErrors().stream()
            .map(e -> new FieldViolation(CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, e.getField()), e.getDefaultMessage(), environment.getProperty(e.getDefaultMessage())))
            .collect(Collectors.toList());
        BusinessError error = BusinessErrors.INVALID_PARAMETERS;
        BaseResponse<Void> data = BaseResponse.ofFailed((String) request.getAttribute("request_id"), error, "Invalid parameters of object: " + exception.getBindingResult().getObjectName(), errors);
        HttpStatus status = error.getHttpStatus();
        return new ResponseEntity<>(data, status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse<Void>> handleException(Exception exception, HttpServletRequest request) {
        BusinessError error = BusinessErrors.INTERNAL_SERVER_ERROR;
        BaseResponse<Void> data = BaseResponse.ofFailed((String) request.getAttribute("request_id"), error, exception.getMessage());
        HttpStatus status = error.getHttpStatus();
        return new ResponseEntity<>(data, status);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<BaseResponse<Void>> handleIllegalArgumentException(IllegalArgumentException exception, HttpServletRequest request) {
        BusinessError error = BusinessErrors.INVALID_PARAMETERS;
        BaseResponse<Void> data = BaseResponse.ofFailed((String) request.getAttribute("request_id"), error, exception.getMessage());
        HttpStatus status = error.getHttpStatus();
        return new ResponseEntity<>(data, status);
    }
}

