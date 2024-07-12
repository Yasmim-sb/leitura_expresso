package com.MS_Customer.service;

import com.MS_Customer.client.ViaCepFeign;
import com.MS_Customer.dto.AddressDTO;
import com.MS_Customer.exceptions.customExceptions.AddressNotFoundException;
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

    @Test
    @DisplayName("create: AddressRequestValidFields > ReturnsAddressDTO")
    void create_withAddressRequestValidFields_ReturnsAddressDTO() {
        when(customerRepository.findById(CUSTOMER01_IN_DB.getId())).thenReturn(Optional.of(CUSTOMER01_IN_DB));
        when(viaCepFeign.searchLocationByCep(ADDRESS01_REQUEST_CORRECT_FIELDS.getCep())).thenReturn(ADDRESS_BY_CEP01);
        when(addressRepository.save(ADDRESS01_TO_CREATE)).thenReturn(ADDRESS01);

        AddressDTO result = addressService.create(ADDRESS01_REQUEST_CORRECT_FIELDS);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(ADDRESS01_DTO);
        verify(customerRepository, times(1)).findById(any());
        verify(viaCepFeign, times(1)).searchLocationByCep(any());
        verify(addressRepository, times((1))).save(any());
    }

    @Test
    @DisplayName("create: InValidCustomerId > CustomerNotFoundException")
    void create_withInValidCustomerId_ThrowCustomerNotFoundException() {
        when(customerRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> addressService.create(ADDRESS01_REQUEST_INCORRECT_CUSTOM_ID));
        verify(customerRepository, times(1)).findById(any());
        verify(viaCepFeign, never()).searchLocationByCep(any());
        verify(addressRepository, never()).save(any());
    }

    @Test
    @DisplayName("create: CEPNotFound > FeignCepNotFoundException")
    void create_withCEPNotFound_ThrowFeignCepNotFoundException() {
        when(customerRepository.findById(CUSTOMER01_IN_DB.getId())).thenReturn(Optional.of(CUSTOMER01_IN_DB));
        when(viaCepFeign.searchLocationByCep(ADDRESS01_REQUEST_NO_FOUND_CEP.getCep())).thenReturn(ADDRESS_BY_CEP_NOT_FOUND);

        assertThrows(FeignCepNotFoundException.class, () -> addressService.create(ADDRESS01_REQUEST_NO_FOUND_CEP));
        verify(customerRepository, times(1)).findById(any());
        verify(viaCepFeign, times(1)).searchLocationByCep(any());
        verify(addressRepository, never()).save(any());
    }

    @Test
    @DisplayName("update: AddressRequestValidFields_Id_Customer_correct > ReturnsAddressDTO")
    void update_withAddressRequestValidFields_Id_Customer_correct_ReturnsAddressDTO() {
        when(addressRepository.findById(ADDRESS01_TO_UPDATE.getId())).thenReturn(Optional.of(ADDRESS01));
        when(customerRepository.findById(ADDRESS02_REQUEST_CORRECT_FIELDS.getCustomerId())).thenReturn(Optional.of(CUSTOMER01_IN_DB));
        when(viaCepFeign.searchLocationByCep(ADDRESS02_REQUEST_CORRECT_FIELDS.getCep())).thenReturn(ADDRESS_BY_CEP02);
        when(addressRepository.save(ADDRESS01_ATT01)).thenReturn(ADDRESS01_ATT01);

        AddressDTO result = addressService.update(ADDRESS01_TO_UPDATE.getId(), ADDRESS02_REQUEST_CORRECT_FIELDS);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(ADDRESS02_DTO);
        verify(customerRepository, times(1)).findById(any());
        verify(viaCepFeign, times(1)).searchLocationByCep(any());
        verify(addressRepository, times((1))).save(any());
    }

    @Test
    @DisplayName("update: InvalidIdAddress > AddressNotFoundException")
    void update_withInvalidIdAddress_ThrowAddressNotFoundException() {
        when(addressRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(AddressNotFoundException.class, () -> addressService.update(999L, ADDRESS02_REQUEST_CORRECT_FIELDS));
        verify(addressRepository, times((1))).findById(any());
        verify(viaCepFeign, never()).searchLocationByCep(any());
        verify(addressRepository, never()).save(any());
    }

    @Test
    @DisplayName("update: InvalidCustomerId > CustomerNotFoundException")
    void update_withInvalidCustomerId_ThrowCustomerNotFoundException() {
        when(addressRepository.findById(ADDRESS01_TO_UPDATE.getId())).thenReturn(Optional.of(ADDRESS01));
        when(customerRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> addressService.update(ADDRESS01_TO_UPDATE.getId(), ADDRESS01_REQUEST_INCORRECT_CUSTOM_ID));
        verify(customerRepository, times(1)).findById(any());
        verify(viaCepFeign, never()).searchLocationByCep(any());
        verify(addressRepository, never()).save(any());
    }

    @Test
    @DisplayName("update: CEPNotFound > FeignCepNotFoundException")
    void update_withCEPNotFound_ThrowFeignCepNotFoundException() {
        when(customerRepository.findById(CUSTOMER01_IN_DB.getId())).thenReturn(Optional.of(CUSTOMER01_IN_DB));
        when(addressRepository.findById(ADDRESS01_TO_UPDATE.getId())).thenReturn(Optional.of(ADDRESS01));
        when(viaCepFeign.searchLocationByCep(ADDRESS01_REQUEST_NO_FOUND_CEP.getCep())).thenReturn(ADDRESS_BY_CEP_NOT_FOUND);

        assertThrows(FeignCepNotFoundException.class, () -> addressService.update(CUSTOMER01_IN_DB.getId(), ADDRESS01_REQUEST_NO_FOUND_CEP));
        verify(customerRepository, times(1)).findById(any());
        verify(viaCepFeign, times(1)).searchLocationByCep(any());
        verify(addressRepository, never()).save(any());
    }

    @Test
    @DisplayName("delete: AddressIdValid > Void")
    void delete_withAddressIdValid_Void() {
        when(addressRepository.findById(1L)).thenReturn(Optional.of(ADDRESS01));

        addressService.delete(1L);

        verify(addressRepository, times(1)).findById(1L);
        verify(addressRepository, times(1)).delete(ADDRESS01);
    }

    @Test
    @DisplayName("delete: AddressIdInvalid > AddressNotFoundException")
    void delete_withAddressIdValidInvalid_ThrowAddressNotFoundException() {
        when(addressRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(AddressNotFoundException.class, () -> addressService.delete(99L));
    }


}
