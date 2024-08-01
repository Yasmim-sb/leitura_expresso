package com.leituraexpresso.challenge.mscustomer.web;

import com.leituraexpresso.challenge.mscustomer.controllers.CustomerControllerImpl;
import com.leituraexpresso.challenge.mscustomer.services.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

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
