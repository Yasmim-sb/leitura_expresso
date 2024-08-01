package com.MS_Customer.dto.response;

import com.MS_Customer.dto.CustomerDTO;
import com.MS_Customer.entities.Address;
import com.MS_Customer.entities.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressResponse{
    @JsonIgnore
    private Long id;

    private String state;

    private String city;

    private String district;

    private String street;

    private String number;

    private String cep;

    private String complement;
}
