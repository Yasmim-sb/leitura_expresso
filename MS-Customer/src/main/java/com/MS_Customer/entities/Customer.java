package com.MS_Customer.entities;

import com.MS_Customer.enums.SexEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @Column(unique = true)
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}",
    message = "CPF must be in the format 000.000.000-00")
    private String cpf;

    private LocalDate birthdate;

    @Column(unique = true)
    @Email
    @NotBlank
    private String email;

    @Size(min = 6)
    @NotBlank
    private String password;

    private boolean active = true;
}
