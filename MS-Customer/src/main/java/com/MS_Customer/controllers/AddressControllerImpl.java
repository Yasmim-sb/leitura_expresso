package com.MS_Customer.controllers;

import com.MS_Customer.dto.AddressDTO;
import com.MS_Customer.interfaces.AddressController;
import com.MS_Customer.request.AddressRequest;
import com.MS_Customer.services.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class AddressControllerImpl implements AddressController {

    private final AddressService addressService;

    @Override
    @PostMapping("/address")
    public ResponseEntity<AddressDTO> createAddress(@RequestBody @Valid AddressRequest request) {
        AddressDTO addressDTO = addressService.create(request);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}")
        .buildAndExpand(addressDTO.getId()).toUri();

        return ResponseEntity.created(uri).body(addressDTO);
    }

}
