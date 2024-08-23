package com.leituraexpresso.challenge.mscustomer.controllers;

import com.leituraexpresso.challenge.mscustomer.dto.CustomerDTO;
import com.leituraexpresso.challenge.mscustomer.dto.requests.CustomerRequestDTO;
import com.leituraexpresso.challenge.mscustomer.dto.response.CustomerResponseDTO;
import com.leituraexpresso.challenge.mscustomer.exceptions.customExceptions.ConflictException;
import com.leituraexpresso.challenge.mscustomer.interfaces.CustomerController;
import com.leituraexpresso.challenge.mscustomer.request.CustomerNewPasswordRequest;
import com.leituraexpresso.challenge.mscustomer.services.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class CustomerControllerImpl implements CustomerController {

    private final CustomerService customerService;

    @PostMapping("/customers")
    public ResponseEntity<CustomerResponseDTO> createCustomer(@RequestBody @Valid CustomerRequestDTO customerRequestDTO){
        var customerDTOResponse = customerService.createCustomer(customerRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerDTOResponse);
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @RequestBody @Valid CustomerDTO customerDTO) {
        var customerS = customerService.updateCustomer(id, customerDTO);
        return ResponseEntity.status(HttpStatus.OK).body(customerS);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
        var customer = customerService.getCustomerById(id);
        return ResponseEntity.ok().body(customer);
    }

    @Override
    @PutMapping("/customers/{id}/password")
    public ResponseEntity<Void> changePasswordCustomer(
        @PathVariable Long id,
        @AuthenticationPrincipal UserDetails customerAuthenticated,
        @RequestBody @Valid CustomerNewPasswordRequest newPasswordRequest
    )
    {
        customerService.updatePassword(id, customerAuthenticated, newPasswordRequest);
        return ResponseEntity.noContent().build();
    }

}
