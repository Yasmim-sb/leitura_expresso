package com.MS_Customer.service;

import com.MS_Customer.exceptions.customExceptions.CustomerNotFoundException;
import com.MS_Customer.repositories.CustomerRepository;
import com.MS_Customer.services.CustomerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.MS_Customer.common.CustomerConstants.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.MethodName.class)
class CustomerServiceTests {

    @InjectMocks
    CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

//    @Test
//    @DisplayName("updatePassword: CorrectPassword > Void")
//    void updatePassword_withCorrectPassword_Void() {
//        when(customerRepository.findById(1L)).thenReturn(Optional.of(CUSTOMER01_IN_DB));
//
//        customerService.updatePassword(1L, CUSTOM_CORRECT_PASSWORD_REQUEST);
//
//        verify(customerRepository, times(1)).findById(any());
//        verify(customerRepository, times(1)).save(any());
//    }
//
//    @Test
//    @DisplayName("updatePassword: IdCustomerDoNotExists > ThrowCustomerNotFoundException")
//    void updatePassword_withIdCustomerDoNotExists_ThrowCustomerNotFoundException() {
//        when(customerRepository.findById(99L)).thenReturn(Optional.empty());
//
//        assertThrows(CustomerNotFoundException.class, () -> customerService.updatePassword(99L, CUSTOM_CORRECT_PASSWORD_REQUEST));
//        verify(customerRepository, times(1)).findById(any());
//        verify(customerRepository, never()).save(any());
//    }

//    @Test
//    @DisplayName("update: IdCustomerDoNotExists -> ThrowCustomerNotFoundException")
//    void update_IdCustomerDoNotExists_ThrowCustomerNotFoundException() {
//        when(customerRepository.findById(-1L)).thenReturn(Optional.of(CUSTOMER02_IN_DB));
//
//        assertThrows(CustomerNotFoundException.class, ()
//                -> customerService.updateCustomer(-1L, CUSTOMER02));
//
//        verify(customerRepository, never()).save(any());
//
//    }

}
