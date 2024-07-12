package com.MS_Customer.services;

import com.MS_Customer.client.ViaCepFeign;
import com.MS_Customer.client.models.AddressByCep;
import com.MS_Customer.dto.AddressDTO;
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
}
