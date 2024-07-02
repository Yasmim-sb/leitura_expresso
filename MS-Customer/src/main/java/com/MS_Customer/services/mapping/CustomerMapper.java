package com.MS_Customer.services.mapping;

import com.MS_Customer.dto.CustomerDTO;
import com.MS_Customer.entities.Customer;

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

        return customer;
    }
}
