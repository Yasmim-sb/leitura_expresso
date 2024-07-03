package com.MS_Customer.dto;

import com.MS_Customer.entities.UserRole;
import com.MS_Customer.enums.SexEnum;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

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

    private UserRole role = UserRole.USER;
}
