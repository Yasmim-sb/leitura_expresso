package com.MS_Customer.services;

import com.MS_Customer.client.ViaCepFeign;
import com.MS_Customer.client.models.AddressByCep;
import com.MS_Customer.dto.AddressDTO;
import com.MS_Customer.dto.CustomerDTO;
import com.MS_Customer.dto.response.AddressResponse;
import com.MS_Customer.entities.Address;
import com.MS_Customer.entities.Customer;
import com.MS_Customer.exceptions.customExceptions.AddressNotFoundException;
import com.MS_Customer.exceptions.customExceptions.CustomerNotFoundException;
import com.MS_Customer.exceptions.customExceptions.FeignCepNotFoundException;
import com.MS_Customer.exceptions.customExceptions.NotPossibleToAlterAddressException;
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

        return new AddressDTO(salveAddress(request.getCep(), request, getCustomByCustomerId(request.getCustomerId())));
    }

    public AddressDTO update(Long id, AddressRequest request) {
        return buildingAddressDTO(id, request);
    }

    public void delete(Long id){
        addressRepository.delete(getAddress(id));
    }

    private AddressDTO buildingAddressDTO(Long id, AddressRequest request) {
        Address address = getAddress(id);
        Customer customer = getCustomByCustomerId(request.getCustomerId());

        checkCurrentlyCustomer(address.getCustomerId().getId(), customer.getId());

        return new AddressDTO(updateAddress(searchByCep(request.getCep()), request, customer, address));
    }

    private Address updateAddress(AddressByCep byCep, AddressRequest request, Customer customer, Address address){
        return addressRepository.save(new Address(byCep, request, customer, address));
    }

    private Address getAddress(Long id) {
        return addressRepository.findById(id).orElseThrow(AddressNotFoundException::new);
    }

    private void checkCurrentlyCustomer(Long idCustomerByAddress, Long idCustomerBySearch) {
        if (!idCustomerBySearch.equals(idCustomerByAddress))
            throw new NotPossibleToAlterAddressException();
    }

    private Address salveAddress(String byCep, AddressRequest request, Customer customer) {
        return addressRepository.save(new Address(searchByCep(byCep), request, customer));
    }

    private Customer getCustomByCustomerId(Long customerId) {
        return customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
    }

    private AddressByCep searchByCep(String cep) {
        AddressByCep byCep = viaCepFeign.searchLocationByCep(cep);

        checkCep(byCep.getErro().equalsIgnoreCase("true"));

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
