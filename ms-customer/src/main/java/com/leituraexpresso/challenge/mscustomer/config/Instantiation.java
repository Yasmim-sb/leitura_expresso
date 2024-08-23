package com.leituraexpresso.challenge.mscustomer.config;

import com.leituraexpresso.challenge.mscustomer.entities.Address;
import com.leituraexpresso.challenge.mscustomer.entities.Customer;
import com.leituraexpresso.challenge.mscustomer.repositories.AddressRepository;
import com.leituraexpresso.challenge.mscustomer.repositories.CustomerRepository;
import com.leituraexpresso.challenge.mscustomer.enums.SexEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.Arrays;

@Configuration
@RequiredArgsConstructor
public class Instantiation implements CommandLineRunner {
    private final AddressRepository addressRepository;

    private final CustomerRepository customerRepository;

    @Override
    public void run(String... args){
        addressRepository.deleteAll();
        customerRepository.deleteAll();

        String password1 = new BCryptPasswordEncoder().encode("123264654");
        Customer customer1 = Customer.builder()
        .firstName("Raimundo")
        .lastName("Silva")
        .sex(SexEnum.MASCULINO)
        .cpf("444.666.888-95")
        .birthdate(LocalDate.of(1998, 10, 11 ))
        .email("r@mail.com")
        .password(password1)
        .active(true)
        .build();


        String password2 = new BCryptPasswordEncoder().encode("123264654");
        Customer customer2 = Customer.builder()
        .firstName("Rita")
        .lastName("Gonçalves")
        .sex(SexEnum.FEMININO)
        .cpf("454.667.884-91")
        .birthdate(LocalDate.of(1991, 9, 8 ))
        .email("rita@mail.com")
        .password(password2)
        .active(true)
        .build();

        customerRepository.saveAll(Arrays.asList(customer1, customer2));

        Address address1 = Address.builder()
        .state("BA")
        .city("Feira de Santana")
        .district("Tomba")
        .street("Rua tabatinga")
        .number("01")
        .cep("44090488")
        .complement("")
        .customerId(customer1)
        .build();


        Address address2 = Address.builder()
        .state("SP")
        .city("São Paulo")
        .district("Sé")
        .street("Praça da Sé")
        .number("7854")
        .cep("01001-000")
        .complement("")
        .customerId(customer2)
        .build();

        addressRepository.saveAll(Arrays.asList(address1, address2));
    }


}
