package com.MS_Customer.dto;

import com.MS_Customer.dto.response.AddressResponse;
import com.MS_Customer.enums.SexEnum;
//import com.MS_Customer.validation.ValidYear;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private SexEnum sex;

    private String cpf;

//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
//    @ValidYear(min = 1900)
    private LocalDate birthdate;

    private String email;

    private String password;

    private boolean active = true;

    private List<AddressResponse> addressList;

}
