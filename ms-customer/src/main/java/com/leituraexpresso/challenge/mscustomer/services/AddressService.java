package com.leituraexpresso.challenge.mscustomer.services;

import com.leituraexpresso.challenge.mscustomer.exceptions.customExceptions.CustomerNotFoundException;
import com.leituraexpresso.challenge.mscustomer.exceptions.customExceptions.FeignCepNotFoundException;
import com.leituraexpresso.challenge.mscustomer.exceptions.customExceptions.NotPossibleToAlterAddressException;
import com.leituraexpresso.challenge.mscustomer.repositories.AddressRepository;
import com.leituraexpresso.challenge.mscustomer.repositories.CustomerRepository;
import com.leituraexpresso.challenge.mscustomer.client.ViaCepFeign;
import com.leituraexpresso.challenge.mscustomer.client.models.AddressByCep;
import com.leituraexpresso.challenge.mscustomer.dto.AddressDTO;
import com.leituraexpresso.challenge.mscustomer.dto.CustomerDTO;
import com.leituraexpresso.challenge.mscustomer.dto.response.AddressResponse;
import com.leituraexpresso.challenge.mscustomer.entities.Address;
import com.leituraexpresso.challenge.mscustomer.entities.Customer;
import com.leituraexpresso.challenge.mscustomer.exceptions.customExceptions.AddressNotFoundException;
import com.leituraexpresso.challenge.mscustomer.request.AddressRequest;
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

        return byCep;
    }

    private void checkCep(boolean check) {
        if (check)
            throw new FeignCepNotFoundException();
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
