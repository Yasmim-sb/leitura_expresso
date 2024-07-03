package com.MS_Customer.exceptions.build;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCodeEnum {
    NOT_FOUND("Data not found"),

    CUSTOMER_NOT_FOUND("Customer not found"),

    NO_ADDRESS_BY_CEP_FOUND("This cep isn't have a valid address"),

    BAD_REQUEST("Invalid data"),

    SYSTEM_ERROR("Unavailable server");

    private final String message;
}
