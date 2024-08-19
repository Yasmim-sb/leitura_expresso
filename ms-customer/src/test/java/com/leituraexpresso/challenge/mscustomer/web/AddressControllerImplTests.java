package com.leituraexpresso.challenge.mscustomer.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leituraexpresso.challenge.mscustomer.exceptions.customExceptions.AddressNotFoundException;
import com.leituraexpresso.challenge.mscustomer.services.AddressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.WebApplicationContext;

import static com.leituraexpresso.challenge.mscustomer.common.AddressConstants.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//@WebMvcTest(AddressControllerImpl.class)
@SpringBootTest
class AddressControllerImplTests {

    MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
        .apply(springSecurity())
        .build();
    }

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    AddressService addressService;

    @Test
    @DisplayName("create: validFields > ReturnAddressDTO : Status_201")
    void create_withValidFields_ReturnAddressDTO_Status201() throws Exception{
        when(addressService.create(ADDRESS01_REQUEST_CORRECT_FIELDS)).thenReturn(ADDRESS01_DTO);

        mockMvc.perform(post("/v1/address")
        .with(user("Pedro").authorities(new SimpleGrantedAuthority("USER")))
        .content(objectMapper.writeValueAsString(ADDRESS01_REQUEST_CORRECT_FIELDS))
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());

    }

    @Test
    @DisplayName("create: InvalidFields > MethodArgumentNotValidException: Status_400")
    void create_withInValidFields_ThrowMethodArgumentNotValidException_Status400() throws Exception{

        mockMvc.perform(post("/v1/address")
        .with(user("Pedro").authorities(new SimpleGrantedAuthority("USER")))
        .content(objectMapper.writeValueAsString(ADDRESS01_REQUEST_INVALID_FIELDS))
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException));
    }

    @Test
    @DisplayName("update: validFields > ReturnAddressDTO : Status_200")
    void update_withValidFields_ReturnAddressDTO_Status200() throws Exception {
        when(addressService.update(2L, ADDRESS01_REQUEST_CORRECT_FIELDS)).thenReturn(ADDRESS01_DTO);

        mockMvc.perform(put("/v1/address/{id}", 2L)
        .with(user("Pedro").authorities(new SimpleGrantedAuthority("USER")))
        .content(objectMapper.writeValueAsString(ADDRESS01_REQUEST_CORRECT_FIELDS))
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
    }

    @Test
    @DisplayName("update: InvalidFields > ThrowMethodArgumentNotValidException : Status_400")
    void update_withInvalidFields_ThrowMethodArgumentNotValidException_Status400() throws Exception {

        mockMvc.perform(put("/v1/address/{id}", 2L)
        .with(user("Pedro").authorities(new SimpleGrantedAuthority("USER")))
        .content(objectMapper.writeValueAsString(ADDRESS01_REQUEST_INVALID_FIELDS))
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException));
    }


    @Test
    @DisplayName("delete: AddressIdValid > ReturnsVoid : Status_204")
    void delete_withAddressIdValid_ReturnsVoid_Status_204() throws Exception {
        doNothing().when(addressService).delete(ADDRESS01_DTO.getId());

        mockMvc.perform(delete("/v1/address/{id}", ADDRESS01_DTO.getId())
        .with(user("Pedro").authorities(new SimpleGrantedAuthority("USER")))
        )
        .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("delete: AddressIdInvalid > AddressNotFoundException : Status_404")
    void delete_withAddressIdValidInvalid_ThrowAddressNotFoundException_Status_404() throws Exception {
        doThrow(AddressNotFoundException.class).when(addressService).delete(99L);

        mockMvc.perform(delete("/v1/address/{id}", 99L)
        .with(user("Pedro").authorities(new SimpleGrantedAuthority("USER")))
        )
        .andExpect(status().isNotFound());
    }
}
