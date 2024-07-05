package com.MS_Customer.exceptions.build;

import com.MS_Customer.exceptions.customExceptions.CustomerNotFound;
import com.MS_Customer.exceptions.customExceptions.NotAllowedException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

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

    public Problem(ErrorCodeEnum errorCode, NotAllowedException exception){
        this.code = Integer.parseInt(exception.getMessageErrorCode());
        this.status = errorCode.name();
        this.message = errorCode.getMessage();
    }
    public Problem(ErrorCodeEnum errorCode, CustomerNotFound exception){
        this.code = Integer.parseInt(exception.getMessageErrorCode());
        this.status = errorCode.name();
        this.message = errorCode.getMessage();
    }
}
