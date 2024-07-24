package com.MS_Customer.services;

import com.MS_Customer.client.ViaCepFeign;
import com.MS_Customer.client.models.AddressByCep;
import com.MS_Customer.dto.AddressDTO;
import com.MS_Customer.dto.CustomerDTO;
import com.MS_Customer.dto.response.AddressResponse;
import com.MS_Customer.entities.Address;
import com.MS_Customer.entities.Customer;
import com.MS_Customer.exceptions.customExceptions.CustomerNotFoundException;
import com.MS_Customer.exceptions.customExceptions.FeignCepNotFoundException;
import com.MS_Customer.repositories.AddressRepository;
import com.MS_Customer.repositories.CustomerRepository;
import com.MS_Customer.request.AddressRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final ViaCepFeign viaCepFeign;
    private final AddressRepository addressRepository;
    private final CustomerRepository customerRepository;

    public AddressDTO create(AddressRequest request) {
        Customer customer = getCustomerByCustomerId(request.getCustomerId());
        Address address = saveAddress(request, customer);

        return new AddressDTO(address);
    }

    private Address saveAddress(AddressRequest request, Customer customer) {
        AddressByCep addressByCep = searchByCep(request.getCep());

        Address address = Address.builder()
        .state(addressByCep.getUf().getNome())
        .city(addressByCep.getLocalidade())
        .district(request.getDistrict())
        .street(request.getStreet())
        .number(request.getNumber())
        .cep(request.getCep())
        .complement(request.getComplement())
        .customerId(customer)
        .build();

        return addressRepository.save(address);
    }

    private Customer getCustomerByCustomerId(Long customerId) {
        return customerRepository.findById(customerId)
        .orElseThrow(CustomerNotFoundException::new);
    }

    private AddressByCep searchByCep(String cep) {
        AddressByCep addressByCep = viaCepFeign.searchLocationByCep(cep);

        checkCep(addressByCep.getErro().equalsIgnoreCase("true"));

        return addressByCep;
    }

    private void checkCep(boolean check) {
        if (check) {
            throw new FeignCepNotFoundException();
        }
    }

    public CustomerDTO convertToCustomerDTO(Customer customer) {
        List<AddressDTO> addressDTOList = customer.getAddressList().stream()
        .map(AddressDTO::new)
        .collect(Collectors.toList());

        List<AddressResponse> AddressResponse = List.of();
        return new CustomerDTO(
        customer.getId(),
        customer.getFirstName(),
        customer.getLastName(),
        customer.getSex(),
        customer.getCpf(),
        customer.getBirthdate(),
        customer.getEmail(),
        customer.getPassword(),
        customer.isActive(),
        AddressResponse
        );
    }
}
