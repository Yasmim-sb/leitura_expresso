package com.MS_Customer.exceptions.customExceptions;

import org.springframework.http.HttpStatus;

public class ConflictException extends Exception{
    private final String messageErrorCode = "CONFLICT";
    private final HttpStatus httpStatus = HttpStatus.CONFLICT;

    public ConflictException(String message) {
        super(message);
    }

    public String getMessageErrorCode() {
        return messageErrorCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
