package com.MS_Customer.common;

import com.MS_Customer.client.models.AddressByCep;
import com.MS_Customer.dto.AddressDTO;
import com.MS_Customer.entities.Address;
import com.MS_Customer.entities.Customer;
import com.MS_Customer.enums.SexEnum;
import com.MS_Customer.enums.StateUFEnum;
import com.MS_Customer.request.AddressRequest;

import java.time.LocalDate;

public class AddressConstants {

    public static AddressRequest ADDRESS01_REQUEST_CORRECT_FIELDS = AddressRequest.builder()
    .street("Street Test 01")
    .number("12")
    .cep("01001-000")
    .complement("Complement test 01")
    .customerId(1L)
    .build();

    public static AddressRequest ADDRESS01_REQUEST_INCORRECT_CUSTOM_ID = AddressRequest.builder()
    .street("Street Test 01")
    .number("12")
    .cep("01001-000")
    .complement("Complement test 01")
    .customerId(999L)
    .build();

    public static AddressRequest ADDRESS01_REQUEST_INCORRECT_LENGTH_CEP = AddressRequest.builder()
    .street("Street Test 01")
    .number("12")
    .cep("01001-000000000000")
    .complement("Complement test 01")
    .customerId(1L)
    .build();

    public static AddressRequest ADDRESS01_REQUEST_NO_FOUND_CEP = AddressRequest.builder()
    .street("Street Test 01")
    .number("12")
    .cep("01000-000")
    .complement("Complement test 01")
    .customerId(1L)
    .build();

    public static AddressRequest ADDRESS01_REQUEST_INVALID_FIELDS = AddressRequest.builder()
    .street(" ")
    .number(" ")
    .cep(" ")
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
    .district(ADDRESS_BY_CEP01.getBairro())
    .street(ADDRESS01_REQUEST_CORRECT_FIELDS.getStreet())
    .number(ADDRESS01_REQUEST_CORRECT_FIELDS.getNumber())
    .cep(ADDRESS01_REQUEST_CORRECT_FIELDS.getCep())
    .complement(ADDRESS01_REQUEST_CORRECT_FIELDS.getComplement())
    .customerId(ADDRESS01_REQUEST_CORRECT_FIELDS.getCustomerId())
    .build();

    public static Address ADDRESS01 = Address.builder()
    .id(1L)
    .state(ADDRESS_BY_CEP01.getUf().getNome())
    .city(ADDRESS_BY_CEP01.getLocalidade())
    .district(ADDRESS_BY_CEP01.getBairro())
    .street(ADDRESS01_REQUEST_CORRECT_FIELDS.getStreet())
    .number(ADDRESS01_REQUEST_CORRECT_FIELDS.getNumber())
    .cep(ADDRESS01_REQUEST_CORRECT_FIELDS.getCep())
    .complement(ADDRESS01_REQUEST_CORRECT_FIELDS.getComplement())
    .customerId(ADDRESS01_REQUEST_CORRECT_FIELDS.getCustomerId())
    .build();

    public static AddressDTO ADDRESS01_DTO = AddressDTO.builder()
    .id(1L)
    .state(ADDRESS_BY_CEP01.getUf().getNome())
    .city(ADDRESS_BY_CEP01.getLocalidade())
    .district(ADDRESS_BY_CEP01.getBairro())
    .street(ADDRESS01_REQUEST_CORRECT_FIELDS.getStreet())
    .number(ADDRESS01_REQUEST_CORRECT_FIELDS.getNumber())
    .cep(ADDRESS01_REQUEST_CORRECT_FIELDS.getCep())
    .complement(ADDRESS01_REQUEST_CORRECT_FIELDS.getComplement())
    .customerId(ADDRESS01_REQUEST_CORRECT_FIELDS.getCustomerId())
    .build();


}
