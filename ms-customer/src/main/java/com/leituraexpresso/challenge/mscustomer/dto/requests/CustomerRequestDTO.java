package com.leituraexpresso.challenge.mscustomer.dto.requests;

import com.leituraexpresso.challenge.mscustomer.enums.SexEnum;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record CustomerRequestDTO(
    @NotBlank (message = "FirstName void")
    String firstName,

    @NotBlank (message = "LastName void")
    String lastName,

    @NotNull (message = "generous incorrect")
    SexEnum sex,

    @NotBlank
    @Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}",
    message = "CPF must be in the format 000.000.000-00")
    String cpf,

    @NotNull(message = "The date don't be null")
    LocalDate birthdate,

    @Email
    @NotBlank (message = "Email void")
    String email,

    @Size(min = 6, message = "Password need at last 6 characters")
    @NotBlank (message = "Password don't be blank")
    String password,

    Boolean active
) {
}
