package com.leituraexpresso.challenge.mscustomer.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leituraexpresso.challenge.mscustomer.controllers.CustomerControllerImpl;
import com.leituraexpresso.challenge.mscustomer.dto.CustomerDTO;
import com.leituraexpresso.challenge.mscustomer.enums.ErrorCodeEnum;
import com.leituraexpresso.challenge.mscustomer.exceptions.customExceptions.CustomerNotFoundException;
import com.leituraexpresso.challenge.mscustomer.infra.security.TokenService;
import com.leituraexpresso.challenge.mscustomer.repositories.CustomerRepository;
import com.leituraexpresso.challenge.mscustomer.services.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest(CustomerControllerImpl.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class CustomerControllerImplTests {
  
    public static final String BASE_URL = "/v1/customers/";
    public static final String ID_URL = "/v1/customers/1";
    public static final Long INVALID_CUSTOMER_ID = 999L;
    private static final String INVALID_ID = "abc";

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

    @MockBean
    TokenService tokenService;

    @MockBean
    CustomerRepository customerRepository;

    @MockBean
    CustomerControllerImpl customerControllerImpl;

    @Test
    @DisplayName("getCustomerById: ValidId > CustomerDTO : Status_200")
    @WithMockUser(value = "pedro", authorities = "USER")
    void getCustomerById_withValidId_CustomerDTO_Status200() throws Exception {
        when(customerService.getCustomerById(anyLong())).thenReturn(CustomerDTO.builder().build());

        MvcResult result = mockMvc
                .perform(get(ID_URL)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    @DisplayName("getCustomerById: InvalidID > CustomerNotFoundException: Status_404")
    @WithMockUser(value = "pedro", authorities = "USER")
    void getCustomerById_withInvalidId_CustomerNotFoundException_Status404() throws Exception {
        when(customerService.getCustomerById(INVALID_CUSTOMER_ID)).thenThrow(CustomerNotFoundException.class);

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get(BASE_URL, INVALID_CUSTOMER_ID)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();

        String expectedErrorMessage = ErrorCodeEnum.NOT_FOUND.getMessage();
        String actualResponseBody = result.getResponse().getContentAsString();

        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
        assertTrue(actualResponseBody.contains(expectedErrorMessage));

    }

    @Test
    @DisplayName("getCustomerById: Invalid Argument Id > MethodArgumentTypeMismatchException")
    @WithMockUser(value = "pedro", authorities = "USER")
    void getCustomerById_InvalidArgument_MethodArgumentTypeMismatchException() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + INVALID_ID)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

        String expectedErrorMessage = ErrorCodeEnum.BAD_REQUEST.getMessage();
        String actualResponseBody = result.getResponse().getContentAsString();

        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        assertTrue(actualResponseBody.contains(expectedErrorMessage));
    }

    @Test
    @DisplayName("getCustomerById: No Argument Id > NoResourceFoundException")
    @WithMockUser(value = "pedro", authorities = "USER")
    void getCustomerById_NoArgumentId_NoResourceFoundException() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();

        String expectedErrorMessage = ErrorCodeEnum.NOT_FOUND.getMessage();
        String actualResponseBody = result.getResponse().getContentAsString();

        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
        assertTrue(actualResponseBody.contains(expectedErrorMessage));
    }

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
