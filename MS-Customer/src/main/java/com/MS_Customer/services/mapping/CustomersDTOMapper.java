package com.MS_Customer.services.mapping;

import com.MS_Customer.dto.CustomerDTO;
import com.MS_Customer.entities.Customer;

public class CustomersDTOMapper {

    public CustomerDTO createCustomerDTO(Customer customer){
        var customerDTO = new CustomerDTO();

        customerDTO.setId(customer.getId());
        customerDTO.setCpf(customer.getCpf());
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setLastName(customer.getLastName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setPassword(customer.getPassword());
        customerDTO.setSex(customer.getSex());
        customerDTO.setBirthdate(customer.getBirthdate());

        return customerDTO;
    }

}
