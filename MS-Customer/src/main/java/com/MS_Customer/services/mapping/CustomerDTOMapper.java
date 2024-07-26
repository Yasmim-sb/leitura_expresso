package com.MS_Customer.services.mapping;

import com.MS_Customer.dto.CustomerDTO;
import com.MS_Customer.dto.response.AddressResponse;
import com.MS_Customer.entities.Address;
import com.MS_Customer.entities.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@Component
public class CustomerDTOMapper {

    private final AddressToAddressResponse addressResponse;

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

        List<Address> addressList = customer.getAddressList();

        List<AddressResponse> addressResponseList = addressList.stream()
                .map(addressResponse::createAddressResponse)
                .collect(Collectors.toList());

        customerDTO.setAddressList(addressResponseList);

        return customerDTO;
    }
}
