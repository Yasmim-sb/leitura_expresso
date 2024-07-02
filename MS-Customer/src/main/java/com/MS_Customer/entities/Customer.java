package com.MS_Customer.entities;

import com.MS_Customer.enums.SexEnum;
import jakarta.persistence.Entity;
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

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3)
    private String firstName;

    @Size(min = 3)
    private String lastName;

    private SexEnum sex;

    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}",
    message = "CPF must be in the format 000.000.000-00")
    private String cpf;

    @DateTimeFormat()
    private LocalDate birthdate;

    @Email
    @NotBlank
    private String email;

    @Size(min = 6)
    private String password;

    private boolean active;
}
