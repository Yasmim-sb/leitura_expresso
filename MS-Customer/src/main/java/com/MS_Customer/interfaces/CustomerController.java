package com.MS_Customer.interfaces;

import com.MS_Customer.dto.CustomerNewPasswordDTO;
import org.springframework.http.ResponseEntity;

public interface CustomerController {

    public ResponseEntity<Void> changePasswordCustomer(Long id, CustomerNewPasswordDTO newPasswordDTO);
}
