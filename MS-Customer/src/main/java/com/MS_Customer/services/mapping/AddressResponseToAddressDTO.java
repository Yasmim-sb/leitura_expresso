//package com.MS_Customer.services.mapping;
//
//import com.MS_Customer.dto.AddressDTO;
//import com.MS_Customer.dto.response.AddressResponse;
//import org.springframework.stereotype.Component;
//
//@Component
//public class AddressResponseToAddressDTO {
//    public AddressResponse createAddressResponse(AddressDTO addressDTO){
//        var response = new AddressResponse();
//
//        response.setState(addressDTO.getState());
//        response.setCity(addressDTO.getCity());
//        response.setDistrict(addressDTO.getDistrict());
//        response.setStreet(addressDTO.getStreet());
//        response.setNumber(addressDTO.getNumber());
//        response.setCep(addressDTO.getCep());
//        response.setComplement(addressDTO.getComplement());;
//
//        return response;
//    }
//}
