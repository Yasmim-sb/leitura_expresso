package com.leituraexpresso.challenge.mscustomer.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.leituraexpresso.challenge.mscustomer.entities.Address;
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

    public AddressResponse(Address address){
        id = address.getId();
        state = address.getState();
        city = address.getCity();
        district = address.getDistrict();
        street = address.getStreet();
        number = address.getNumber();
        cep = address.getCep();
        complement = address.getComplement();
    }
}
