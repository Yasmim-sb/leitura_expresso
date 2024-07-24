package com.MS_Customer.services.mapping;

import com.MS_Customer.dto.CustomerDTO;
import com.MS_Customer.dto.response.AddressResponse;
import com.MS_Customer.entities.Address;
import com.MS_Customer.entities.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@AllArgsConstructor
public class CustomerMapper {

    private final AddressToAddressResponse address;

    public CustomerDTO createCustomer(CustomerDTO customerDTO){
        var customer = new Customer();

        customer.setId(customerDTO.getId());
        customer.setCpf(customerDTO.getCpf());
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPassword(customerDTO.getPassword());
        customer.setSex(customerDTO.getSex());
        customer.setBirthdate(customerDTO.getBirthdate());


        List<AddressResponse> addressList = customerDTO.getAddressList();

        List<Address> addressL = Collections.singletonList(address.createAddress((AddressResponse) addressList));

        customer.setAddressList(addressL);

        return customerDTO;
    }
}
