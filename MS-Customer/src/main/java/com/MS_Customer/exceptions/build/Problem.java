package com.MS_Customer.exceptions.build;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class Problem {

    private final int code;

    private final String status;

    private final String message;

    public Problem(HttpStatus httpStatus){
        this.code = httpStatus.value();
        this.status = httpStatus.name();
        this.message = ErrorCodeEnum.SYSTEM_ERROR.getMessage();
    }

    public Problem(String message, HttpStatus status){
        this.code = status.value();
        this.status = status.name();
        this.message = message;
    }

}
