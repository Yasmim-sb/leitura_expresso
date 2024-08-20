package com.leituraexpresso.challenge.mscustomer.dto;

import com.leituraexpresso.challenge.mscustomer.dto.response.AddressResponse;
import com.leituraexpresso.challenge.mscustomer.entities.Customer;
import com.leituraexpresso.challenge.mscustomer.enums.CustomerRole;
import com.leituraexpresso.challenge.mscustomer.enums.SexEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private SexEnum sex;

    private String cpf;

    private LocalDate birthdate;

    private String email;

    private String password;

    private boolean active = true;

    private List<AddressResponse> addressList = new ArrayList<>();

    private CustomerRole role;

    public CustomerDTO(Long id, String firstName, String lastName, SexEnum sex, String cpf, LocalDate birthdate, String email, String password, boolean active, List<AddressResponse> addressResponse) {
    }

    public CustomerDTO(Customer customer){
        id = customer.getId();
        firstName = customer.getFirstName();
        lastName = customer.getLastName();
        sex = customer.getSex();
        cpf = customer.getCpf();
        birthdate = customer.getBirthdate();
        email = customer.getEmail();
        password = customer.getPassword();
        active = customer.isActive();
        addressList.addAll(
            customer.getAddressList().stream()
            .map(AddressResponse::new).toList()
        );
    }
}
