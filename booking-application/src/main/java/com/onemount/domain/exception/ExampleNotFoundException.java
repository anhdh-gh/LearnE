package com.onemount.domain.exception;

import static com.onemount.domain.exception.BookingErrors.EXAMPLE_NOT_FOUND_ERROR;

/**
 * Example of exception declaration
 */
public class ExampleNotFoundException extends BookingException {
    public ExampleNotFoundException() {
        super(EXAMPLE_NOT_FOUND_ERROR);
    }

    public ExampleNotFoundException(String message) {
        super(EXAMPLE_NOT_FOUND_ERROR, message);
    }
}
