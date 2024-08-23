package com.leituraexpresso.challenge.mscustomer.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCodeEnum {
    NOT_FOUND("Data not found"),

    CUSTOMER_NOT_FOUND("Customer not found"),

    EMAIL_ALREADY_IN_USE("Email already in use"),

    CPF_ALREADY_IN_USE("CPF already in use"),

    NO_ADDRESS_BY_CEP_FOUND("This cep isn't have a valid address"),

    BAD_REQUEST("Invalid data"),

    NOT_ALL0WED_CHANGE_PASSWORD_FROM_OTHER_CUSTOMER("not allowed to change password from other customer"),

    SYSTEM_ERROR("Unavailable server"),

    TOKEN_EXPIRATED("The Token has expired on "),

    NOT_ALLOWED("Not allowed"),

    NOT_ALLOWED_ALTER_ADDRESS("Not allowed alter address from other customer, the id need to be the same");

    private final String message;
}
