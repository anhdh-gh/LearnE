package com.onemount.domain.exception;

import lombok.Getter;

import java.io.Serializable;

/**
 * Base Booking Exception Class
 * When you create a new exception, it must extend this class.
 * For more detail please read in README.md
 */
@Getter
public class BookingException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1905122041950251207L;
    private final BookingBusinessError errorCode;

    public BookingException(BookingBusinessError errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public BookingException(BookingBusinessError errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public BookingException(BookingBusinessError error, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = error;
    }
}
