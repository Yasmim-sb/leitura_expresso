package com.leituraexpresso.challenge.mscustomer.exceptions.customExceptions;

import com.leituraexpresso.challenge.mscustomer.enums.ErrorCodeEnum;
import org.springframework.http.HttpStatus;

public class NotAllowedException extends StandardCustomException{
    public NotAllowedException() {
        super(ErrorCodeEnum.NOT_ALLOWED, HttpStatus.FORBIDDEN);
    }
}
