package com.MS_Customer.exceptions.customExceptions;

import com.MS_Customer.enums.ErrorCodeEnum;
import org.springframework.http.HttpStatus;

public class NotPossibleToAlterAddressException extends StandardCustomException{
    public NotPossibleToAlterAddressException() {
        super(ErrorCodeEnum.NOT_ALLOWED_ALTER_ADDRESS, HttpStatus.BAD_REQUEST);
    }
}
