package com.MS_Customer.common;

import com.MS_Customer.dto.CustomerDTO;
import com.MS_Customer.entities.Customer;
import com.MS_Customer.enums.SexEnum;
import com.MS_Customer.request.CustomerNewPasswordRequest;

import java.time.LocalDate;

public class CustomerConstants {

    public static Customer CUSTOMER01_IN_DB = Customer.builder()
    .id(1L)
    .firstName("Pedro")
    .lastName("Silva")
    .sex(SexEnum.MASCULINO)
    .cpf("117.885.222.96")
    .birthdate(LocalDate.of(1980, 1, 17))
    .email("pedro@email.com")
    .password("1234567")
    .active(true)
    .addressList(null)
    .build();

    public static Customer CUSTOMER02_IN_DB = Customer.builder()
            .id(-1L)
            .firstName("Pedro")
            .lastName("Silva")
            .sex(SexEnum.MASCULINO)
            .cpf("117.885.222.96")
            .birthdate(LocalDate.of(1980, 1, 17))
            .email("pedro@email.com")
            .active(true)
            .addressList(null)
            .build();

    public static CustomerDTO CUSTOMER02 = CustomerDTO.builder()
            .id(-1L)
            .firstName("Pedro")
            .lastName("Silva")
            .sex(SexEnum.MASCULINO)
            .cpf("117.885.222.96")
            .birthdate(LocalDate.of(1980, 1, 17))
            .email("pedro@email.com")
            .active(true)
            .addressList(null)
            .build();

    public static CustomerNewPasswordRequest CUSTOM_CORRECT_PASSWORD_REQUEST = CustomerNewPasswordRequest.builder()
    .newPassword("123456789")
    .build();

    public static CustomerNewPasswordRequest CUSTOM_INCORRECT_PASSWORD_REQUEST = CustomerNewPasswordRequest.builder()
    .newPassword("  ")
    .build();
}
