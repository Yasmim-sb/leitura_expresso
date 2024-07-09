package com.MS_Customer.services;

import com.MS_Customer.client.ViaCepFeign;
import com.MS_Customer.client.models.AddressByCep;
import com.MS_Customer.dto.AddressDTO;
import com.MS_Customer.entities.Address;
import com.MS_Customer.entities.Customer;
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
        return new AddressDTO(salveAddress(request.getCep(), request, getCustomByCustomerId(request.getCustomerId())));
    }

    private Address salveAddress(String byCep, AddressRequest request, Customer customer){
       return addressRepository.save(new Address(searchByCep(byCep), request, customer));
    }

    private Customer getCustomByCustomerId(Long customerId){
        return customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
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
