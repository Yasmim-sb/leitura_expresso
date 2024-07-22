package com.MS_Customer.services.mapping;

import com.MS_Customer.dto.AddressDTO;
import com.MS_Customer.dto.CustomerDTO;
import com.MS_Customer.dto.response.AddressResponse;
import com.MS_Customer.entities.Address;
import com.MS_Customer.entities.Customer;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
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

        if (customer.getAddressList() != null) {
            customerDTO.setAddressList(customer.getAddressList().stream()
            .map(address -> new AddressDTO(
            address.getId(),
            address.getState(),
            address.getCity(),
            address.getDistrict(),
            address.getStreet(),
            address.getNumber(),
            address.getCep(),
            address.getComplement(),
            customer
            ))
            .collect(Collectors.toList()));
        }

        return customerDTO;
    }
}
