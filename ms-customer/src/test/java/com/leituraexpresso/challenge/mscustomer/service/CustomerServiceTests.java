package com.leituraexpresso.challenge.mscustomer.service;

import com.leituraexpresso.challenge.mscustomer.repositories.CustomerRepository;
import com.leituraexpresso.challenge.mscustomer.services.CustomerService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

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
