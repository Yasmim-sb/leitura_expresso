package com.MS_Customer.dto;

import com.MS_Customer.dto.response.AddressResponse;
import com.MS_Customer.entities.validate.ValidAge;
import com.MS_Customer.enums.CustomerRole;
import com.MS_Customer.enums.SexEnum;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "A data de nascimento não pode ser nula.")
    @ValidAge
    private LocalDate birthdate;

    private String email;

    private String password;

    private boolean active = true;

    private List<AddressResponse> addressList = new ArrayList<>();

    private CustomerRole role;

    public CustomerDTO(Long id, String firstName, String lastName, SexEnum sex, String cpf, LocalDate birthdate, String email, String password, boolean active, List<AddressResponse> addressResponse) {
    }
}
