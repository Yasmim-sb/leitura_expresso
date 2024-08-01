package com.leituraexpresso.challenge.mscustomer.exceptions.customExceptions;

import org.springframework.http.HttpStatus;

public class ConflictException extends RuntimeException{
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
