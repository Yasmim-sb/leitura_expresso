package com.MS_Customer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

    private Long id;

    private String state;

    private String city;

    private String district;

    private String street;

    private String number;

    private String cep;

    private String complement;

}
