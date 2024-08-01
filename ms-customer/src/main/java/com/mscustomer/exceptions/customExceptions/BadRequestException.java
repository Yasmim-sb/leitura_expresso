package com.MS_Customer.exceptions.customExceptions;

import org.springframework.http.HttpStatus;

public class BadRequestException extends RuntimeException {

    private final String messageErrorCode = "BAD_REQUEST";
    private final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    public BadRequestException(String message) {
        super(message);
    }

    public String getMessageErrorCode(){
        return this.messageErrorCode;
    }

    public HttpStatus getHttpStatus(){
        return this.httpStatus;
    }
}
