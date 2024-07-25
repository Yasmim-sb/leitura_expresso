package com.MS_Customer.interfaces;

import com.MS_Customer.request.CustomerNewPasswordRequest;
import org.springframework.http.ResponseEntity;

public interface CustomerController {

    public ResponseEntity<Void> changePasswordCustomer(Long id, CustomerNewPasswordRequest newPasswordDTO);
}
