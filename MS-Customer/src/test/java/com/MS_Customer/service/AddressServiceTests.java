package com.MS_Customer.service;

import com.MS_Customer.client.ViaCepFeign;
import com.MS_Customer.dto.AddressDTO;
import com.MS_Customer.exceptions.customExceptions.CustomerNotFoundException;
import com.MS_Customer.exceptions.customExceptions.FeignCepNotFoundException;
import com.MS_Customer.repositories.AddressRepository;
import com.MS_Customer.repositories.CustomerRepository;
import com.MS_Customer.services.AddressService;
import feign.FeignException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Optional;

import static com.MS_Customer.common.AddressConstants.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.MethodName.class)
class AddressServiceTests {

    @InjectMocks
    AddressService addressService;

    @Mock
    AddressRepository addressRepository;

    @Mock
    CustomerRepository customerRepository;

    @Mock
    ViaCepFeign viaCepFeign;

//    @Test
//    @DisplayName("create: AddressRequestValidFields > ReturnsAddressDTO")
//    void create_withAddressRequestValidFields_ReturnsAddressDTO(){
//        when(customerRepository.findById(CUSTOMER01_IN_DB.getId())).thenReturn(Optional.of(CUSTOMER01_IN_DB));
//        when(viaCepFeign.searchLocationByCep(ADDRESS01_REQUEST_CORRECT_FIELDS.getCep())).thenReturn(ADDRESS_BY_CEP01);
//        when(addressRepository.save(ADDRESS01_TO_CREATE)).thenReturn(ADDRESS01);
//
//        AddressDTO result = addressService.create(ADDRESS01_REQUEST_CORRECT_FIELDS);
//
//        assertThat(result).isNotNull();
//        assertThat(result).isEqualTo(ADDRESS01_DTO);
//        verify(customerRepository, times(1)).findById(any());
//        verify(viaCepFeign, times(1)).searchLocationByCep(any());
//        verify(addressRepository, times((1))).save(any());
//    }

    @Test
    @DisplayName("create: InValidCustomerId > CustomerNotFoundException")
    void create_withInValidCustomerId_ThrowCustomerNotFoundException(){
        when(customerRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> addressService.create(ADDRESS01_REQUEST_INCORRECT_CUSTOM_ID));
        verify(customerRepository, times(1)).findById(any());
        verify(viaCepFeign, never()).searchLocationByCep(any());
        verify(addressRepository, never()).save(any());
    }

    @Test
    @DisplayName("create: InValidLengthCEP > FeignException")
    void create_withInValidLengthCEP_ThrowFeignException(){
        when(customerRepository.findById(CUSTOMER01_IN_DB.getId())).thenReturn(Optional.of(CUSTOMER01_IN_DB));
        when(viaCepFeign.searchLocationByCep(ADDRESS01_REQUEST_INCORRECT_LENGTH_CEP.getCep())).thenThrow(FeignException.class);

        assertThrows(FeignException.class, () -> addressService.create(ADDRESS01_REQUEST_INCORRECT_LENGTH_CEP));
        verify(customerRepository, times(1)).findById(any());
        verify(viaCepFeign, times(1)).searchLocationByCep(any());
        verify(addressRepository, never()).save(any());
    }

    @Test
    @DisplayName("create: CEPNotFound > FeignCepNotFoundException")
    void create_withInValidCEPNotFound_ThrowFeignCepNotFoundException(){
        when(customerRepository.findById(CUSTOMER01_IN_DB.getId())).thenReturn(Optional.of(CUSTOMER01_IN_DB));
        when(viaCepFeign.searchLocationByCep(ADDRESS01_REQUEST_NO_FOUND_CEP.getCep())).thenReturn(ADDRESS_BY_CEP_NOT_FOUND);

        assertThrows(FeignCepNotFoundException.class, () -> addressService.create(ADDRESS01_REQUEST_NO_FOUND_CEP));
        verify(customerRepository, times(1)).findById(any());
        verify(viaCepFeign, times(1)).searchLocationByCep(any());
        verify(addressRepository, never()).save(any());
    }

}
