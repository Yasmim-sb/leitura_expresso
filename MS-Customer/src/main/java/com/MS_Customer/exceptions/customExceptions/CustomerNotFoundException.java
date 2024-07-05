package com.MS_Customer.exceptions.customExceptions;

import com.MS_Customer.exceptions.build.ErrorCodeEnum;
import org.springframework.http.HttpStatus;

public class CustomerNotFoundException extends StandardCustomException{
    public CustomerNotFoundException() {
        super(ErrorCodeEnum.CUSTOMER_NOT_FOUND, HttpStatus.NOT_FOUND);
    }
}
