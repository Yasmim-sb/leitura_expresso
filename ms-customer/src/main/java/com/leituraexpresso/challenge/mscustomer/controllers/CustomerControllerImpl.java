package com.leituraexpresso.challenge.mscustomer.controllers;

import com.leituraexpresso.challenge.mscustomer.dto.CustomerDTO;
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
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody @Valid CustomerDTO customerDTO) throws BadRequestException, ConflictException {
        var customerDTOResponse = customerService.createCustomer(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerDTOResponse.getBody());
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @RequestBody @Valid CustomerDTO customerDTO){
        var customerS = customerService.updateCustomer(id, customerDTO);
        return ResponseEntity.status(HttpStatus.OK).body(customerS);
    }
    @GetMapping("/customers/{id}")
    public CustomerDTO getCustomer(@PathVariable Long id) {
        CustomerDTO customerDTO = customerService.getCustomer(id);
        return new ResponseEntity<>(customerDTO, HttpStatus.OK).getBody();
    }

    @Override
    @PutMapping("/customers/{id}/password")
    public ResponseEntity<Void> changePasswordCustomer(
    @PathVariable Long id,
    @AuthenticationPrincipal UserDetails customerAuthenticated,
    @RequestBody @Valid CustomerNewPasswordRequest newPasswordRequest) {
        customerService.updatePassword(id, customerAuthenticated, newPasswordRequest);
        return ResponseEntity.noContent().build();
    }

}
