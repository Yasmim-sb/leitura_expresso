package com.MS_Customer.dto;

import com.MS_Customer.enums.CustomerRole;
import com.MS_Customer.enums.SexEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

    private CustomerRole role;

}
