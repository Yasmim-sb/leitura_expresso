package com.leituraexpresso.challenge.mscustomer.services.mapping;

import com.leituraexpresso.challenge.mscustomer.dto.CustomerDTO;
import com.leituraexpresso.challenge.mscustomer.entities.Address;
import com.leituraexpresso.challenge.mscustomer.entities.Customer;
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
