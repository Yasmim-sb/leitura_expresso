package com.MS_Customer.service;

import com.MS_Customer.client.ViaCepFeign;
import com.MS_Customer.dto.AddressDTO;
import com.MS_Customer.repositories.AddressRepository;
import com.MS_Customer.repositories.CustomerRepository;
import com.MS_Customer.services.AddressService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.MS_Customer.common.AddressConstants.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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
    void create_withAddressRequestValidFields_ReturnsAddressDTO(){
        when(customerRepository.findById(CUSTOMER01_IN_DB.getId())).thenReturn(Optional.of(CUSTOMER01_IN_DB));
        when(viaCepFeign.searchLocationByCep(ADDRESS01_REQUEST_CORRECT_FIELDS.getCep())).thenReturn(ADDRESS_BY_CEP01);
        when(addressRepository.save(any())).thenReturn(ADDRESS01_DTO);

        AddressDTO result = addressService.create(ADDRESS01_REQUEST_CORRECT_FIELDS);

        assertThat(result).isNotNull();
    }
}
