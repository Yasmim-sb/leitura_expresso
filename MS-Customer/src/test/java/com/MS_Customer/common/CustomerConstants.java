package com.MS_Customer.common;

import com.MS_Customer.dto.CustomerDTO;
import com.MS_Customer.entities.Customer;
import com.MS_Customer.enums.SexEnum;
import com.MS_Customer.request.CustomerNewPasswordRequest;

import java.time.LocalDate;

public class CustomerConstants {

    public static CustomerDTO CUSTOMERDTO01_IN_DB = CustomerDTO.builder()
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

    public static Customer CUSTOMER_WITH_PASSWORD = Customer.builder()
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

    //apagar e substituir
    public static CustomerDTO CUSTOMERDTO_WITH_PASSWORD = CustomerDTO.builder()
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

    public static CustomerDTO CUSTOMER_WITH_BIRTHDATE_INVALID = CustomerDTO.builder()
            .id(1L)
            .firstName("Pedro")
            .lastName("Silva")
            .sex(SexEnum.MASCULINO)
            .cpf("117.885.222.74")
            .birthdate(LocalDate.of(2009, 1, 17))
            .email("pedro@email.com")
            .active(true)
            .addressList(null)
            .build();

    public static Customer CUSTOMER_WITHOUT_PASSWORD = Customer.builder()
            .id(1L)
            .firstName("savana")
            .lastName("Silva")
            .sex(SexEnum.FEMININO)
            .cpf("117.885.222.96")
            .birthdate(LocalDate.of(2009, 1, 17))
            .email("pedro@email.com")
            .active(true)
            .addressList(null)
            .build();

    public static CustomerDTO CUSTOMERDTO_WITHOUT_PASSWORD = CustomerDTO.builder()
            .id(1L)
            .firstName("savana")
            .lastName("Silva")
            .sex(SexEnum.FEMININO)
            .cpf("117.885.222.96")
            .birthdate(LocalDate.of(2009, 1, 17))
            .email("pedro@email.com")
            .active(true)
            .addressList(null)
            .build();
    public static Customer CUSTOMER_INVALID_ID = Customer.builder()
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

    public static CustomerDTO CUSTOMERDTO_INVALID_ID = CustomerDTO.builder()
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
