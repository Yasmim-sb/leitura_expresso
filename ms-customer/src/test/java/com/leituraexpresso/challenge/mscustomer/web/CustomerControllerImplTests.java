package com.leituraexpresso.challenge.mscustomer.web;

import com.leituraexpresso.challenge.mscustomer.controllers.CustomerControllerImpl;
import com.leituraexpresso.challenge.mscustomer.services.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.WebApplicationContext;

import static com.leituraexpresso.challenge.mscustomer.common.CustomerConstants.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest(CustomerControllerImpl.class)
@SpringBootTest
class CustomerControllerImplTests {

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
    CustomerService customerService;

    @Test
    @DisplayName("changePasswordCustomer: ValidPassword > Void : Status_204")
    @WithMockUser(value = "pedro", authorities = "USER")
    void changePasswordCustomer_withValidPassword_Void_Status204() throws Exception {
        doNothing()
        .when(customerService)
        .updatePassword(1L, CUSTOMER01_IN_DB_USERDETAILS, CUSTOM_CORRECT_PASSWORD_REQUEST);

        mockMvc.perform(put("/v1/customers/{id}/password", 1L)
        .content(objectMapper.writeValueAsString(CUSTOM_CORRECT_PASSWORD_REQUEST))
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());

        verify(customerService, times(1)).updatePassword(any(), any(), any());
    }

    @Test
    @DisplayName("changePasswordCustomer: InvalidPassword > MethodArgumentNotValidException : Status_400")
    @WithMockUser(value = "pedro", authorities = "USER")
    void changePasswordCustomer_withInvalidPassword_ThrowsMethodArgumentNotValidException_Status400() throws Exception{

        mockMvc.perform(put("/v1/customers/{id}/password", 1L)
        .content(objectMapper.writeValueAsString(CUSTOM_INCORRECT_PASSWORD_REQUEST))
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException));
    }

    @Test
    @DisplayName("changePasswordCustomer: InvalidAuthenticatedCustomer > Void : Status_403")
    void changePasswordCustomer_withInvalidAuthenticatedCustomer_Status403() throws Exception{

        mockMvc.perform(put("/v1/customers/{id}/password", 1L)
        .content(objectMapper.writeValueAsString(CUSTOM_INCORRECT_PASSWORD_REQUEST))
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isForbidden());
    }

    // Test token expired
}
