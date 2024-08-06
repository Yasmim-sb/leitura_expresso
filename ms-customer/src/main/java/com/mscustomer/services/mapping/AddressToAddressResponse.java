package com.MS_Customer.services.mapping;

import com.MS_Customer.dto.response.AddressResponse;
import com.MS_Customer.entities.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressToAddressResponse {
    public AddressResponse createAddressResponse(Address address){
        var response = new AddressResponse();

        response.setState(address.getState());
        response.setCity(address.getCity());
        response.setDistrict(address.getDistrict());
        response.setStreet(address.getStreet());
        response.setNumber(address.getNumber());
        response.setCep(address.getCep());
        response.setComplement(address.getComplement());;

        return response;
    }

    public Address createAddress(AddressResponse response){
        var address = new Address();

        address.setState(response.getState());
        address.setCity(response.getCity());
        address.setDistrict(response.getDistrict());
        address.setStreet(response.getStreet());
        address.setNumber(response.getNumber());
        address.setCep(response.getCep());
        address.setComplement(response.getComplement());;

        return address;
    }
}
