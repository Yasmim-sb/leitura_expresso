package com.leituraexpresso.challenge.mscustomer.interfaces;

import com.leituraexpresso.challenge.mscustomer.request.CustomerNewPasswordRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

public interface CustomerController {

    public ResponseEntity<Void> changePasswordCustomer(
        Long id,
        UserDetails customerAuthenticated,
        CustomerNewPasswordRequest newPasswordDTO
    );
}
