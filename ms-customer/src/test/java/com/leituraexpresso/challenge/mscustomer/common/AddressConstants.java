package com.leituraexpresso.challenge.mscustomer.common;

import com.leituraexpresso.challenge.mscustomer.client.models.AddressByCep;
import com.leituraexpresso.challenge.mscustomer.dto.AddressDTO;
import com.leituraexpresso.challenge.mscustomer.entities.Address;
import com.leituraexpresso.challenge.mscustomer.entities.Customer;
import com.leituraexpresso.challenge.mscustomer.enums.SexEnum;
import com.leituraexpresso.challenge.mscustomer.enums.StateUFEnum;
import com.leituraexpresso.challenge.mscustomer.request.AddressRequest;

import java.time.LocalDate;

public class AddressConstants {

    public static AddressRequest ADDRESS01_REQUEST_CORRECT_FIELDS = AddressRequest.builder()
    .street("Street Test 01")
    .number("12")
    .cep("01001-000")
    .district("Center")
    .complement("Complement test 01")
    .customerId(1L)
    .build();

    public static AddressRequest ADDRESS02_REQUEST_CORRECT_FIELDS = AddressRequest.builder()
    .street("Street Test 02")
    .number("1000")
    .cep("44600-000")
    .district("Center")
    .complement("Complement 02")
    .customerId(1L)
    .build();

    public static AddressRequest ADDRESS03_REQUEST_CORRECT_FIELDS = AddressRequest.builder()
    .street("Street Test 01")
    .number("12")
    .cep("01001-000")
    .district("Center")
    .complement("Complement test 01")
    .customerId(2L)
    .build();

    public static AddressRequest ADDRESS01_REQUEST_INCORRECT_CUSTOM_ID = AddressRequest.builder()
    .street("Street Test 01")
    .number("12")
    .cep("01001-000")
    .district("Center")
    .complement("Complement test 01")
    .customerId(999L)
    .build();

    public static AddressRequest ADDRESS01_REQUEST_INCORRECT_LENGTH_CEP = AddressRequest.builder()
    .street("Street Test 01")
    .number("12")
    .cep("01001-000000000000")
    .district("Center")
    .complement("Complement test 01")
    .customerId(1L)
    .build();

    public static AddressRequest ADDRESS01_REQUEST_NO_FOUND_CEP = AddressRequest.builder()
    .street("Street Test 01")
    .number("12")
    .cep("01000-000")
    .district("Center")
    .complement("Complement test 01")
    .customerId(1L)
    .build();

    public static AddressRequest ADDRESS01_REQUEST_INVALID_FIELDS = AddressRequest.builder()
    .street(" ")
    .number(" ")
    .cep(" ")
    .district(" ")
    .complement(" ")
    .customerId(null)
    .build();

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
    .id(2L)
    .firstName("Maria")
    .lastName("Souza")
    .sex(SexEnum.FEMININO)
    .cpf("778.556.222.78")
    .birthdate(LocalDate.of(1984, 1, 19))
    .email("m@email.com")
    .password("1234567")
    .active(true)
    .addressList(null)
    .build();

    public static AddressByCep ADDRESS_BY_CEP01 = AddressByCep.builder()
    .logradouro("Praça da Sé")
    .complemento("lado ímpar")
    .bairro("Sé")
    .localidade("São Paulo")
    .uf(StateUFEnum.SP)
    .cep("01001-000")
    .erro("false")
    .build();

    public static AddressByCep ADDRESS_BY_CEP02 = AddressByCep.builder()
    .logradouro("")
    .complemento("")
    .bairro("")
    .localidade("Ipirá")
    .uf(StateUFEnum.BA)
    .cep("44600-000")
    .erro("false")
    .build();

    public static AddressByCep ADDRESS_BY_CEP_NOT_FOUND = AddressByCep.builder()
    .logradouro(null)
    .complemento(null)
    .bairro(null)
    .localidade(null)
    .uf(null)
    .cep(null)
    .erro("true")
    .build();

    public static Address ADDRESS01_TO_CREATE = Address.builder()
    .state(ADDRESS_BY_CEP01.getUf().getNome())
    .city(ADDRESS_BY_CEP01.getLocalidade())
    .district(ADDRESS01_REQUEST_CORRECT_FIELDS.getDistrict())
    .street(ADDRESS01_REQUEST_CORRECT_FIELDS.getStreet())
    .number(ADDRESS01_REQUEST_CORRECT_FIELDS.getNumber())
    .cep(ADDRESS01_REQUEST_CORRECT_FIELDS.getCep())
    .complement(ADDRESS01_REQUEST_CORRECT_FIELDS.getComplement())
    .customerId(CUSTOMER01_IN_DB)
    .build();

    public static Address ADDRESS01 = Address.builder()
    .id(1L)
    .state(ADDRESS_BY_CEP01.getUf().getNome())
    .city(ADDRESS_BY_CEP01.getLocalidade())
    .district(ADDRESS01_REQUEST_CORRECT_FIELDS.getDistrict())
    .street(ADDRESS01_REQUEST_CORRECT_FIELDS.getStreet())
    .number(ADDRESS01_REQUEST_CORRECT_FIELDS.getNumber())
    .cep(ADDRESS01_REQUEST_CORRECT_FIELDS.getCep())
    .complement(ADDRESS01_REQUEST_CORRECT_FIELDS.getComplement())
    .customerId(CUSTOMER01_IN_DB)
    .build();

    public static Address ADDRESS01_TO_UPDATE = Address.builder()
    .id(ADDRESS01.getId())
    .state(ADDRESS_BY_CEP02.getUf().getNome())
    .city(ADDRESS_BY_CEP02.getLocalidade())
    .district(ADDRESS02_REQUEST_CORRECT_FIELDS.getDistrict())
    .street(ADDRESS02_REQUEST_CORRECT_FIELDS.getStreet())
    .number(ADDRESS02_REQUEST_CORRECT_FIELDS.getNumber())
    .cep(ADDRESS02_REQUEST_CORRECT_FIELDS.getCep())
    .complement(ADDRESS02_REQUEST_CORRECT_FIELDS.getComplement())
    .customerId(CUSTOMER01_IN_DB)
    .build();


    public static Address ADDRESS01_ATT01 = Address.builder()
    .id(ADDRESS01.getId())
    .state(ADDRESS_BY_CEP02.getUf().getNome())
    .city(ADDRESS_BY_CEP02.getLocalidade())
    .district(ADDRESS02_REQUEST_CORRECT_FIELDS.getDistrict())
    .street(ADDRESS02_REQUEST_CORRECT_FIELDS.getStreet())
    .number(ADDRESS02_REQUEST_CORRECT_FIELDS.getNumber())
    .cep(ADDRESS02_REQUEST_CORRECT_FIELDS.getCep())
    .complement(ADDRESS02_REQUEST_CORRECT_FIELDS.getComplement())
    .customerId(CUSTOMER01_IN_DB)
    .build();

    public static Address ADDRESS01_TO_UPDATE_ID_CUSTOMER_INCORRECT = Address.builder()
    .id(ADDRESS01.getId())
    .state(ADDRESS_BY_CEP02.getUf().getNome())
    .city(ADDRESS_BY_CEP02.getLocalidade())
    .district(ADDRESS02_REQUEST_CORRECT_FIELDS.getDistrict())
    .street(ADDRESS02_REQUEST_CORRECT_FIELDS.getStreet())
    .number(ADDRESS02_REQUEST_CORRECT_FIELDS.getNumber())
    .cep(ADDRESS02_REQUEST_CORRECT_FIELDS.getCep())
    .complement(ADDRESS02_REQUEST_CORRECT_FIELDS.getComplement())
    .customerId(CUSTOMER01_IN_DB)
    .build();

    public static AddressDTO ADDRESS01_DTO = AddressDTO.builder()
    .id(1L)
    .state(ADDRESS_BY_CEP01.getUf().getNome())
    .city(ADDRESS_BY_CEP01.getLocalidade())
    .district(ADDRESS01_REQUEST_CORRECT_FIELDS.getDistrict())
    .street(ADDRESS01_REQUEST_CORRECT_FIELDS.getStreet())
    .number(ADDRESS01_REQUEST_CORRECT_FIELDS.getNumber())
    .cep(ADDRESS01_REQUEST_CORRECT_FIELDS.getCep())
    .complement(ADDRESS01_REQUEST_CORRECT_FIELDS.getComplement())
    .customerId(CUSTOMER01_IN_DB)
    .build();

    public static AddressDTO ADDRESS02_DTO = AddressDTO.builder()
    .id(ADDRESS01.getId())
    .state(ADDRESS_BY_CEP02.getUf().getNome())
    .city(ADDRESS_BY_CEP02.getLocalidade())
    .district(ADDRESS02_REQUEST_CORRECT_FIELDS.getDistrict())
    .street(ADDRESS02_REQUEST_CORRECT_FIELDS.getStreet())
    .number(ADDRESS02_REQUEST_CORRECT_FIELDS.getNumber())
    .cep(ADDRESS02_REQUEST_CORRECT_FIELDS.getCep())
    .complement(ADDRESS02_REQUEST_CORRECT_FIELDS.getComplement())
    .customerId(CUSTOMER02_IN_DB)
    .build();

}
