package com.leituraexpresso.challenge.mscustomer.exceptions.customExceptions;

import com.leituraexpresso.challenge.mscustomer.enums.ErrorCodeEnum;
import org.springframework.http.HttpStatus;

public class CustomerNotFoundException extends StandardCustomException{
    public CustomerNotFoundException() {
        super(ErrorCodeEnum.CUSTOMER_NOT_FOUND, HttpStatus.NOT_FOUND);
    }
}
