package com.MS_Customer.exceptions.customExceptions;

import com.MS_Customer.exceptions.build.ErrorCodeEnum;
import org.springframework.http.HttpStatus;

public class NotAllowedException extends StandardCustomException{
    public NotAllowedException() {
        super(ErrorCodeEnum.NOT_ALLOWED, HttpStatus.FORBIDDEN);
    }
}
