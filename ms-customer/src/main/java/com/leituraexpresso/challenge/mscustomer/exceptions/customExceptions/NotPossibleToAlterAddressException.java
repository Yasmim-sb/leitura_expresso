package com.leituraexpresso.challenge.mscustomer.exceptions.customExceptions;

import com.leituraexpresso.challenge.mscustomer.enums.ErrorCodeEnum;
import org.springframework.http.HttpStatus;

public class NotPossibleToAlterAddressException extends StandardCustomException{
    public NotPossibleToAlterAddressException() {
        super(ErrorCodeEnum.NOT_ALLOWED_ALTER_ADDRESS, HttpStatus.BAD_REQUEST);
    }
}
