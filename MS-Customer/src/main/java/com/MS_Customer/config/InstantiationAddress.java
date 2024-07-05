package com.MS_Customer.config;

import com.MS_Customer.entities.Address;
import com.MS_Customer.entities.Customer;
import com.MS_Customer.enums.SexEnum;
import com.MS_Customer.repositories.AddressRepository;
import com.MS_Customer.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Arrays;

@Configuration
@RequiredArgsConstructor
public class InstantiationAddress implements CommandLineRunner {
    private final AddressRepository addressRepository;

    private final CustomerRepository customerRepository;

    @Override
    public void run(String... args){
        addressRepository.deleteAll();
        customerRepository.deleteAll();

        Customer customer1 = Customer.builder()
        .id(33L)
        .firstName("Raimundo")
        .lastName("Silva")
        .sex(SexEnum.MASCULINO)
        .cpf("444.666.88895")
        .birthdate(LocalDate.of(1998, 10, 11 ))
        .email("r@mail.com")
        .password("123264654")
        .active(true)
        .build();


        Customer customer2 = Customer.builder()
        .id(34L)
        .firstName("Rita")
        .lastName("Gonçalves")
        .sex(SexEnum.FEMININO)
        .cpf("454.667.884-91")
        .birthdate(LocalDate.of(1991, 9, 8 ))
        .email("rita@mail.com")
        .password("12311264654")
        .active(true)
        .build();

        customerRepository.saveAll(Arrays.asList(customer1, customer2));

        Address address1 = Address.builder()
        .id(3L)
        .state("BA")
        .city("Feira de Santana")
        .district("Tomba")
        .street("Rua tabatinga")
        .number("01")
        .cep("44090488")
        .complement("")
        .customerId(33L)
        .build();

        Address address2 = Address.builder()
        .id(4L)
        .state("SP")
        .city("Praça da Sé")
        .district("Sé")
        .street("Praça da Sé")
        .number("7854")
        .cep("01001-000")
        .complement("")
        .customerId(34L)
        .build();

        addressRepository.saveAll(Arrays.asList(address1, address2));
    }


}
