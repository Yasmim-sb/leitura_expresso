package com.MS_Customer.interfaces;

import com.MS_Customer.dto.AddressDTO;
import com.MS_Customer.request.AddressRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface AddressController {

    ResponseEntity<AddressDTO> createAddress(@RequestBody AddressRequest request);

    ResponseEntity<AddressDTO> updateAddress(@PathVariable Long id, @RequestBody AddressRequest request);

}
