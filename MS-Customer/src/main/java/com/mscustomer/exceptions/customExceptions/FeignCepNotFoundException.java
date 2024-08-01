package com.MS_Customer.exceptions.customExceptions;

import com.MS_Customer.enums.ErrorCodeEnum;
import org.springframework.http.HttpStatus;

public class FeignCepNotFoundException extends StandardCustomException{
    public FeignCepNotFoundException() {
        super(ErrorCodeEnum.NO_ADDRESS_BY_CEP_FOUND, HttpStatus.NOT_FOUND);
    }
}
