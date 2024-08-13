package com.leituraexpresso.challenge.mscustomer.service;

import com.leituraexpresso.challenge.mscustomer.dto.CustomerDTO;
import com.leituraexpresso.challenge.mscustomer.entities.Customer;
import com.leituraexpresso.challenge.mscustomer.exceptions.customExceptions.CustomerNotFoundException;
import com.leituraexpresso.challenge.mscustomer.repositories.CustomerRepository;
import com.leituraexpresso.challenge.mscustomer.services.CustomerService;
import com.leituraexpresso.challenge.mscustomer.services.mapping.CustomerDTOMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.leituraexpresso.challenge.mscustomer.common.AddressConstants.CUSTOMER02_IN_DB;
import static com.leituraexpresso.challenge.mscustomer.common.CustomerConstants.CUSTOMER02;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.MethodName.class)
class CustomerServiceTests {

    public static final Long ID = 1L;

    @InjectMocks
    CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    @Mock
    CustomerDTOMapper customerDTOMapper;

    @Test
    @DisplayName("getCustomerById: IdCustomerExists > CustomerDTO")
    void getCustomerById_IdCustomerExists_CustomerDTO() {
        Customer customer = CUSTOMER02_IN_DB;
        CustomerDTO expectedResponse = CUSTOMER02;

        when(customerDTOMapper.createCustomerDTO(CUSTOMER02_IN_DB)).thenReturn(expectedResponse);
        when(customerRepository.findById(ID)).thenReturn(Optional.of(customer));

        CustomerDTO response = customerService.getCustomerById(ID);

        assertNotNull(response);
        assertEquals(expectedResponse, response);
        verify(customerRepository).findById(anyLong());
    }

    @Test
    @DisplayName("getCustomerById: IdCustomerDoesNotExist > CustomerNotFoundException")
    void getCustomerById_IdCustomerDoesNotExist_CustomerNotFoundException() {
        when(customerRepository.findById(ID)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> customerService.getCustomerById(ID));
    }
}

