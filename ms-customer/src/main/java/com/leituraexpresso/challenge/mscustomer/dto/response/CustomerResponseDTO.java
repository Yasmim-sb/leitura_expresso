package com.leituraexpresso.challenge.mscustomer.dto.response;

import com.leituraexpresso.challenge.mscustomer.entities.Customer;
import com.leituraexpresso.challenge.mscustomer.enums.SexEnum;

import java.time.LocalDate;

public record CustomerResponseDTO(
    Long id,
    String firstName,
    String lastName,
    SexEnum sex,
    String cpf,
    LocalDate birthdate,
    String email,
    boolean active
) {
    public CustomerResponseDTO(Customer customer){
        this(
            customer.getId(),
            customer.getFirstName(),
            customer.getLastName(),
            customer.getSex(),
            customer.getCpf(),
            customer.getBirthdate(),
            customer.getEmail(),
            customer.isActive()
        );
    }
}
