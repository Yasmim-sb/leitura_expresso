package com.leituraexpresso.challenge.mscustomer.common;

import com.leituraexpresso.challenge.mscustomer.dto.CustomerDTO;
import com.leituraexpresso.challenge.mscustomer.entities.Customer;
import com.leituraexpresso.challenge.mscustomer.enums.SexEnum;
import com.leituraexpresso.challenge.mscustomer.request.CustomerNewPasswordRequest;
import org.springframework.security.core.userdetails.UserDetails;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

import static com.leituraexpresso.challenge.mscustomer.common.AddressConstants.*;

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
    .addressList(Collections.singletonList(ADDRESS01))
    .build();

    public static Customer CUSTOMER03_IN_DB = Customer.builder()
    .id(2L)
    .firstName("Anna")
    .lastName("Oliveira")
    .sex(SexEnum.FEMININO)
    .cpf("117.905.222.55")
    .birthdate(LocalDate.of(1980, 1, 17))
    .email("anna@email.com")
    .password("1234567")
    .active(true)
    .addressList(Collections.singletonList(ADDRESS02))
    .build();

    public static UserDetails CUSTOMER01_IN_DB_USERDETAILS = Customer.builder()
    .id(1L)
    .firstName("Pedro")
    .lastName("Silva")
    .sex(SexEnum.MASCULINO)
    .cpf("117.885.222.96")
    .birthdate(LocalDate.of(1980, 1, 17))
    .email("pedro@email.com")
    .password("1234567")
    .active(true)
    .addressList(Collections.singletonList(ADDRESS01))
    .build();

    public static Customer CUSTOMER03_IN_DB_USERDETAILS = Customer.builder()
    .id(2L)
    .firstName("Anna")
    .lastName("Oliveira")
    .sex(SexEnum.FEMININO)
    .cpf("117.905.222.55")
    .birthdate(LocalDate.of(1980, 1, 17))
    .email("anna@email.com")
    .password("1234567")
    .active(true)
    .addressList(Collections.singletonList(ADDRESS02))
    .build();

    public static Customer CUSTOMER01_IN_DB_UPDATED_PASSWORD = Customer.builder()
    .id(1L)
    .firstName("Pedro")
    .lastName("Silva")
    .sex(SexEnum.MASCULINO)
    .cpf("117.885.222.96")
    .birthdate(LocalDate.of(1980, 1, 17))
    .email("pedro@email.com")
    .password("123456789")
    .active(true)
    .addressList(Collections.singletonList(ADDRESS01))
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
