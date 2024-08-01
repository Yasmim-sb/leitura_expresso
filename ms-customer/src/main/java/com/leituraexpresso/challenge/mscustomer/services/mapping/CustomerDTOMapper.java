package com.leituraexpresso.challenge.mscustomer.services.mapping;

import com.leituraexpresso.challenge.mscustomer.dto.CustomerDTO;
import com.leituraexpresso.challenge.mscustomer.dto.response.AddressResponse;
import com.leituraexpresso.challenge.mscustomer.entities.Address;
import com.leituraexpresso.challenge.mscustomer.entities.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@Component
public class CustomerDTOMapper {

    private final AddressToAddressResponse addressResponse;

    public CustomerDTO createCustomerDTO(Customer customer) {
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
                .map(addressResponse::createAddressResponse) // Utilize addressMapper em vez de addressResponse
                .collect(Collectors.toList());

        customerDTO.setAddressList(addressResponseList);

        return customerDTO;
    }
}
