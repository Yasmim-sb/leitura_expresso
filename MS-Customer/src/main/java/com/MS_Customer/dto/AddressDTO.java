package com.MS_Customer.dto;

import com.MS_Customer.entities.Address;
import com.MS_Customer.request.AddressRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDTO {

    private Long id;

    private String state;

    private String city;

    private String district;

    private String street;

    private String number;

    private String cep;

    private String complement;

    private Long customerId;

    public AddressDTO(Address address){
        this.id = address.getId();
        this.state = address.getState();
        this.city = address.getCity();
        this.district = address.getDistrict();
        this.street = address.getStreet();
        this.number = address.getNumber();
        this.cep = address.getCep();
        this.complement = address.getComplement();
        this.customerId = address.getCustomerId().getId();
    }
}
