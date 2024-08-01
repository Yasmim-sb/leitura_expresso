package com.leituraexpresso.challenge.mscustomer.dto;

import com.leituraexpresso.challenge.mscustomer.entities.Address;
import com.leituraexpresso.challenge.mscustomer.entities.Customer;
import lombok.*;

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

    private Customer customerId;

    public AddressDTO(Address address) {
        this.id = address.getId();
        this.state = address.getState();
        this.city = address.getCity();
        this.district = address.getDistrict();
        this.street = address.getStreet();
        this.number = address.getNumber();
        this.cep = address.getCep();
        this.complement = address.getComplement();
    }


}
