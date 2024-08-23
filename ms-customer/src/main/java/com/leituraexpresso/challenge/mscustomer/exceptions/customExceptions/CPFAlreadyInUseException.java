package com.leituraexpresso.challenge.mscustomer.exceptions.customExceptions;

import com.leituraexpresso.challenge.mscustomer.enums.ErrorCodeEnum;
import org.springframework.http.HttpStatus;

public class CPFAlreadyInUseException extends StandardCustomException{
    public CPFAlreadyInUseException() {
        super(ErrorCodeEnum.CPF_ALREADY_IN_USE, HttpStatus.CONFLICT);
    }
}
