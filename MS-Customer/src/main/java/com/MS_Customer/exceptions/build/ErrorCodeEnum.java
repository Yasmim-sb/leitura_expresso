package com.MS_Customer.exceptions.build;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCodeEnum {
    NOT_FOUND("Data not found"),

    BAD_REQUEST("Invalid data"),

    SYSTEM_ERROR("Unavailable server"),

    NOT_ALLOWED("Not allowed");

    private final String message;
}
