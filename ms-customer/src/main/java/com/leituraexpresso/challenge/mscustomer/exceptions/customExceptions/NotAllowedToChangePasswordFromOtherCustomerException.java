package com.leituraexpresso.challenge.mscustomer.exceptions.customExceptions;

import com.leituraexpresso.challenge.mscustomer.enums.ErrorCodeEnum;
import org.springframework.http.HttpStatus;

public class NotAllowedToChangePasswordFromOtherCustomerException extends StandardCustomException{
    public NotAllowedToChangePasswordFromOtherCustomerException() {
        super(ErrorCodeEnum.NOT_ALL0WED_CHANGE_PASSWORD_FROM_OTHER_CUSTOMER, HttpStatus.BAD_REQUEST);
    }
}
