package com.leituraexpresso.challenge.mscustomer.exceptions.customExceptions;

import com.leituraexpresso.challenge.mscustomer.enums.ErrorCodeEnum;
import org.springframework.http.HttpStatus;

public class AddressNotFoundException extends StandardCustomException{
    public AddressNotFoundException() {
        super(ErrorCodeEnum.NOT_FOUND, HttpStatus.NOT_FOUND);
    }
}
