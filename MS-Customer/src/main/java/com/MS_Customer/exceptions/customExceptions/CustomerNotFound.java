package com.MS_Customer.exceptions.customExceptions;

import com.MS_Customer.exceptions.build.ErrorCodeEnum;
import org.springframework.http.HttpStatus;

public class CustomerNotFound extends StandardCustomException{
    public CustomerNotFound() {
        super(ErrorCodeEnum.NOT_ALLOWED, HttpStatus.FORBIDDEN);
    }
}
