package com.MS_Customer.web;

import com.MS_Customer.controllers.CustomerControllerImpl;
import com.MS_Customer.exceptions.customExceptions.CustomerNotFoundException;
import com.MS_Customer.request.CustomerNewPasswordRequest;
import com.MS_Customer.services.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static com.MS_Customer.common.CustomerConstants.CUSTOM_CORRECT_PASSWORD_REQUEST;
import static com.MS_Customer.common.CustomerConstants.CUSTOM_INCORRECT_PASSWORD_REQUEST;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerControllerImpl.class)
class CustomerControllerImplTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    CustomerService customerService;

//    @Test
//    @DisplayName("changePasswordCustomer: ValidPassword > Void : Status_204")
//    void changePasswordCustomer_withValidPassword_Void_Status204() throws Exception {
//        doNothing().when(customerService).updatePassword(1L, CUSTOM_CORRECT_PASSWORD_REQUEST);
//
//        mockMvc.perform(put("/v1/customers/{id}/password", 1L)
//        .content(objectMapper.writeValueAsString(CUSTOM_CORRECT_PASSWORD_REQUEST))
//        .contentType(MediaType.APPLICATION_JSON))
//        .andExpect(status().isNoContent());
//
//        verify(customerService, times(1)).updatePassword(any(), any());
//    }
//
//    @Test
//    @DisplayName("changePasswordCustomer: ValidPassword > Void : Status_400")
//    void changePasswordCustomer_withInvalidPassword_ThrowsMethodArgumentNotValidException_Status400() throws Exception{
//
//        mockMvc.perform(put("/v1/customers/{id}/password", 1L)
//        .content(objectMapper.writeValueAsString(CUSTOM_INCORRECT_PASSWORD_REQUEST))
//        .contentType(MediaType.APPLICATION_JSON))
//        .andExpect(status().isBadRequest());
//
//    }
}
