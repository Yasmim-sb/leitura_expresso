package com.MS_Customer.controllers;

import com.MS_Customer.dto.CustomerAuthenticationDTO;
import com.MS_Customer.dto.CustomerDTO;
import com.MS_Customer.interfaces.CustomerController;
import com.MS_Customer.repositories.CustomerRepository;
import com.MS_Customer.services.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class CustomerControllerImpl implements CustomerController {

    private final CustomerService customerService;
    private final AuthenticationManager authenticationManager;
    private final CustomerRepository customerRepository;

    @PostMapping("/customers")
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody @Valid CustomerDTO customerDTO) {
        var customerDTOResponse = customerService.createCustomer(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerDTOResponse.getBody());
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid CustomerAuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        return ResponseEntity.ok().body(auth);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable Long id) {
        CustomerDTO customerDTO = customerService.getCustomer(id).getBody();
        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }
}
