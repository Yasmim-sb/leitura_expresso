package com.MS_Customer.web;

import com.MS_Customer.controllers.AddressControllerImpl;
import com.MS_Customer.services.AddressService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(AddressControllerImpl.class)
class AddressControllerImplTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    AddressService addressService;

    @Test
    @DisplayName("create: InValidFields > MethodArgumentNotValidException: 400")
    void create_withInValidFields_ThrowMethodArgumentNotValidException_Status400() {

    }
}
