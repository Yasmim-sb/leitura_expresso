package com.leituraexpresso.challenge.mscustomer.interfaces;

import com.leituraexpresso.challenge.mscustomer.dto.AddressDTO;
import com.leituraexpresso.challenge.mscustomer.request.AddressRequest;
import org.springframework.http.ResponseEntity;

public interface AddressController {

    ResponseEntity<AddressDTO> createAddress(AddressRequest request);

    ResponseEntity<AddressDTO> updateAddress(Long id, AddressRequest request);

    ResponseEntity<String> deleteAddress(Long id);

}
