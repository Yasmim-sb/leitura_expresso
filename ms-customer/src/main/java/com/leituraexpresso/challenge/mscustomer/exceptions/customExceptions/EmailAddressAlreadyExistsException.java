package com.leituraexpresso.challenge.mscustomer.exceptions.customExceptions;

import com.leituraexpresso.challenge.mscustomer.enums.ErrorCodeEnum;
import org.springframework.http.HttpStatus;

public class EmailAddressAlreadyExistsException extends StandardCustomException{
    public EmailAddressAlreadyExistsException() {
        super(ErrorCodeEnum.EMAIL_ALREADY_IN_USE, HttpStatus.CONFLICT);
    }
}
