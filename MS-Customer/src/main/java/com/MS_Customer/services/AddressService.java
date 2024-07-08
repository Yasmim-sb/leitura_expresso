package com.MS_Customer.services;

import com.MS_Customer.client.ViaCepFeign;
import com.MS_Customer.client.models.AddressByCep;
import com.MS_Customer.dto.AddressDTO;
import com.MS_Customer.entities.Address;
import com.MS_Customer.exceptions.customExceptions.CustomerNotFoundException;
import com.MS_Customer.exceptions.customExceptions.FeignCepNotFoundException;
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

    public AddressDTO create(AddressRequest request){
        checkIdCustomerIsValid(request.getCustomerId());

        return new AddressDTO(salveAddress(request.getCep(), request));
    }

    private Address salveAddress(String byCep, AddressRequest request){
       return addressRepository.save(new Address(searchByCep(byCep), request));
    }

    private void checkIdCustomerIsValid(Long customerId){
        if(!customerRepository.existsById(customerId))
            throw new CustomerNotFoundException();
    }

    private AddressByCep searchByCep(String cep){
        AddressByCep byCep = viaCepFeign.searchLocationByCep(cep);

        checkCep(byCep.erro.equalsIgnoreCase("true"));

        return byCep;
    }

    private void checkCep(boolean check){
        if (check)
            throw new FeignCepNotFoundException();
    }
}
