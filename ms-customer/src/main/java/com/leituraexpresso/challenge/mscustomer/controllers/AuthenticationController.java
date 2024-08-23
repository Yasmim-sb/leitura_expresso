package com.leituraexpresso.challenge.mscustomer.controllers;

import com.leituraexpresso.challenge.mscustomer.dto.CustomerAuthenticationDTO;
import com.leituraexpresso.challenge.mscustomer.dto.LoginResponseDTO;
import com.leituraexpresso.challenge.mscustomer.entities.Customer;
import com.leituraexpresso.challenge.mscustomer.infra.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody CustomerAuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());

        var auth = authenticationManager.authenticate(usernamePassword);

        var token  = tokenService.generateToken((Customer) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
}
