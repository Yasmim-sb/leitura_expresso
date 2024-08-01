package com.leituraexpresso.challenge.mscustomer.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCodeEnum {
    NOT_FOUND("Data not found"),

    CUSTOMER_NOT_FOUND("Customer not found"),

    NO_ADDRESS_BY_CEP_FOUND("This cep isn't have a valid address"),

    BAD_REQUEST("Invalid data"),

    SYSTEM_ERROR("Unavailable server"),

    NOT_ALLOWED("Not allowed"),

    NOT_ALLOWED_ALTER_ADDRESS("Not allowed alter address from other customer, the id need to be the same");

    private final String message;
}
