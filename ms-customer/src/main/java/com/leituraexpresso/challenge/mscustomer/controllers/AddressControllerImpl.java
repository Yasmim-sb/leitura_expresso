package com.leituraexpresso.challenge.mscustomer.controllers;

import com.leituraexpresso.challenge.mscustomer.interfaces.AddressController;
import com.leituraexpresso.challenge.mscustomer.dto.AddressDTO;
import com.leituraexpresso.challenge.mscustomer.request.AddressRequest;
import com.leituraexpresso.challenge.mscustomer.services.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/address")
public class AddressControllerImpl implements AddressController {

    private final AddressService addressService;

    @Override
    @PostMapping
    public ResponseEntity<AddressDTO> createAddress(@RequestBody @Valid AddressRequest request) {
        AddressDTO addressDTO = addressService.create(request);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(addressDTO.getId()).toUri();

        return ResponseEntity.created(uri).body(addressDTO);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<AddressDTO> updateAddress(@PathVariable Long id, @RequestBody @Valid AddressRequest request) {
        AddressDTO addressDTO = addressService.update(id, request);

        return ResponseEntity.ok().body(addressDTO);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable Long id) {
        addressService.delete(id);
        return ResponseEntity.ok("Endereço deletado com sucesso!");
    }

}
