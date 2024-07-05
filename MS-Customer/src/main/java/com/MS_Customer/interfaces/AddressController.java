package com.MS_Customer.interfaces;

import com.MS_Customer.dto.AddressDTO;
import com.MS_Customer.request.AddressRequest;
import org.springframework.http.ResponseEntity;

public interface AddressController {

    ResponseEntity<AddressDTO> createAddress(AddressRequest request);
}
