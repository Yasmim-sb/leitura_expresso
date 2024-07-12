package com.MS_Customer.web;

import com.MS_Customer.controllers.AddressControllerImpl;
import com.MS_Customer.request.AddressRequest;
import com.MS_Customer.services.AddressService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Description;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static com.MS_Customer.common.AddressConstants.*;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(AddressControllerImpl.class)
class AddressControllerImplTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    AddressService addressService;

//    @Test
//    @DisplayName("create: validFields > ReturnAddressDTO : Status_201")
//    void create_withValidFields_ReturnAddressDTO_Status201() throws Exception{
//        when(addressService.create(ADDRESS01_REQUEST_CORRECT_FIELDS)).thenReturn(ADDRESS01_DTO);
//
//        mockMvc.perform(post("/v1/address")
//        .content(objectMapper.writeValueAsString(ADDRESS01_REQUEST_CORRECT_FIELDS))
//        .contentType(MediaType.APPLICATION_JSON))
//        .andExpect(status().isCreated())
//        .andExpect(jsonPath("$.id").value(ADDRESS01_DTO.getId()));
//
//    }
//
//    @Test
//    @DisplayName("create: InValidFields > MethodArgumentNotValidException: Status_400")
//    void create_withInValidFields_ThrowMethodArgumentNotValidException_Status400() throws Exception{
//        when(addressService.create(ADDRESS01_REQUEST_INVALID_FIELDS)).thenThrow(MethodArgumentNotValidException.class);
//
//        mockMvc.perform(post("/v1/address")
//        .content(objectMapper.writeValueAsString(ADDRESS01_REQUEST_INVALID_FIELDS))
//        .contentType(MediaType.APPLICATION_JSON))
//        .andExpect(status().isBadRequest());
//    }
}
