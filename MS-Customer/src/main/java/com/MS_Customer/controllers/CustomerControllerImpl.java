package com.MS_Customer.controllers;

import com.MS_Customer.dto.CustomerDTO;
import com.MS_Customer.exceptions.customExceptions.ConflictException;
import com.MS_Customer.request.CustomerNewPasswordRequest;
import com.MS_Customer.interfaces.CustomerController;
import com.MS_Customer.services.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        customerService.updateCustomer(id, customerDTO);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/customers/{id}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable Long id) {
        CustomerDTO customerDTO = customerService.getCustomer(id).getBody();
        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }

    @Override
    @PutMapping("/customers/{id}/password")
    public ResponseEntity<Void> changePasswordCustomer(@PathVariable Long id, @RequestBody @Valid CustomerNewPasswordRequest newPasswordRequest) {
        customerService.updatePassword(id, newPasswordRequest);
        return ResponseEntity.noContent().build();
    }
}
