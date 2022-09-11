package com.onemount.application.api.exceptionhandler;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.CaseFormat;
import com.onemount.application.api.response.dto.BaseResponse;
import com.onemount.application.api.response.dto.FieldViolation;
import com.onemount.domain.exception.BookingBusinessError;
import com.onemount.domain.exception.BookingErrors;
import com.onemount.domain.exception.BookingException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Global exception handler for Rest API
 * It wraps all Booking Application exceptions to predefined structure that was defined in com/onemount/application/api/response/dto/BaseResponse.java
 * Not need to modify
 */
@Controller
@ControllerAdvice
@AllArgsConstructor
public class BookingGlobalExceptionHandler {

    private ObjectMapper objectMapper;

    @ExceptionHandler(BookingException.class)
    public ResponseEntity<BaseResponse<Void>> handleBusinessException(BookingException exception) {
        BaseResponse<Void> data = BaseResponse.ofFailed(exception);
        return new ResponseEntity<>(data, exception.getErrorCode().getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse<Void>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, HttpServletRequest request) {
        List<FieldViolation> errors = exception.getBindingResult().getFieldErrors().stream()
                .map(e -> new FieldViolation(CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, e.getField()), e.getDefaultMessage()))
                .collect(Collectors.toList());
        BookingBusinessError errorCode = BookingErrors.INVALID_PARAMETERS;
        BaseResponse<Void> data = BaseResponse.ofFailed(errorCode, "Invalid parameters of object: " + exception.getBindingResult().getObjectName(), errors);
        HttpStatus status = errorCode.getHttpStatus();
        return new ResponseEntity<>(data, status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse<Void>> handleException(Exception exception, HttpServletRequest request) {
        BookingBusinessError errorCode = BookingErrors.INTERNAL_SERVER_ERROR;
        BaseResponse<Void> data = BaseResponse.ofFailed(errorCode, exception.getMessage());
        HttpStatus status = errorCode.getHttpStatus();
        return new ResponseEntity<>(data, status);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<BaseResponse<Void>> handleIllegalArgumentException(IllegalArgumentException exception, HttpServletRequest request) {
        BookingBusinessError errorCode = BookingErrors.INVALID_PARAMETERS;
        BaseResponse<Void> data = BaseResponse.ofFailed(errorCode, exception.getMessage());
        HttpStatus status = errorCode.getHttpStatus();
        return new ResponseEntity<>(data, status);
    }
}

