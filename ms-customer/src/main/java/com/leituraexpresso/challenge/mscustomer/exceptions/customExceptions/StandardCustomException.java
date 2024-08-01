package com.leituraexpresso.challenge.mscustomer.exceptions.customExceptions;

import com.leituraexpresso.challenge.mscustomer.enums.ErrorCodeEnum;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@Getter
public class StandardCustomException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    private final String messageErrorCode;

    private final HttpStatus httpStatus;

    public StandardCustomException(ErrorCodeEnum errorCodeEnum, HttpStatus httpStatus){
        this.messageErrorCode = errorCodeEnum.getMessage();
        this.httpStatus = httpStatus;
    }

}
