package com.MS_Customer.services.mapping;

import com.MS_Customer.dto.CustomerDTO;
import com.MS_Customer.entities.Address;
import com.MS_Customer.entities.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CustomerMapper {

    private final AddressToAddressResponse address;

    public Customer createCustomer(CustomerDTO customerDTO) {
    var customer = new Customer();

    customer.setId(customerDTO.getId());
    customer.setCpf(customerDTO.getCpf());
    customer.setFirstName(customerDTO.getFirstName());
    customer.setLastName(customerDTO.getLastName());
    customer.setEmail(customerDTO.getEmail());
    customer.setPassword(customerDTO.getPassword());
    customer.setSex(customerDTO.getSex());
    customer.setBirthdate(customerDTO.getBirthdate());

    List<Address> addressList = customerDTO.getAddressList().stream()
            .map(address::createAddress)
            .collect(Collectors.toList());

    customer.setAddressList(addressList);

    return customer;
    }
}
