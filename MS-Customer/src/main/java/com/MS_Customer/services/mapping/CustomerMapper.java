package com.MS_Customer.services.mapping;

import com.MS_Customer.dto.CustomerDTO;
import com.MS_Customer.entities.Address;
import com.MS_Customer.entities.Customer;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CustomerMapper {
    public Customer createCustomer(CustomerDTO customerDTO){
        var customer = new Customer();

        customer.setId(customerDTO.getId());
        customer.setCpf(customerDTO.getCpf());
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPassword(customerDTO.getPassword());
        customer.setSex(customerDTO.getSex());
        customer.setBirthdate(customerDTO.getBirthdate());

        if (customerDTO.getAddressList() != null) {
            customer.setAddressList(customerDTO.getAddressList().stream()
            .map(addressDTO -> new Address(
            addressDTO.getId(),
            addressDTO.getState(),
            addressDTO.getCity(),
            addressDTO.getDistrict(),
            addressDTO.getStreet(),
            addressDTO.getNumber(),
            addressDTO.getCep(),
            addressDTO.getComplement(),
            customer
            ))
            .collect(Collectors.toList()));
        }

        return customer;
    }
}
