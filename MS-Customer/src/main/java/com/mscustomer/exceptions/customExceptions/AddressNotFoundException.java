package com.MS_Customer.exceptions.customExceptions;

import com.MS_Customer.enums.ErrorCodeEnum;
import org.springframework.http.HttpStatus;

public class AddressNotFoundException extends StandardCustomException{
    public AddressNotFoundException() {
        super(ErrorCodeEnum.NOT_FOUND, HttpStatus.NOT_FOUND);
    }
}
