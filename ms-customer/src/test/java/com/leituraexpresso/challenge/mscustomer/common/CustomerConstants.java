package com.leituraexpresso.challenge.mscustomer.common;

import com.leituraexpresso.challenge.mscustomer.dto.CustomerDTO;
import com.leituraexpresso.challenge.mscustomer.entities.Customer;
import com.leituraexpresso.challenge.mscustomer.enums.SexEnum;
import com.leituraexpresso.challenge.mscustomer.request.CustomerNewPasswordRequest;

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
