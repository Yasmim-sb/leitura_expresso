package com.leituraexpresso.challenge.mscustomer.exceptions.customExceptions;

import com.leituraexpresso.challenge.mscustomer.enums.ErrorCodeEnum;
import org.springframework.http.HttpStatus;

public class FeignCepNotFoundException extends StandardCustomException{
    public FeignCepNotFoundException() {
        super(ErrorCodeEnum.NO_ADDRESS_BY_CEP_FOUND, HttpStatus.NOT_FOUND);
    }
}
