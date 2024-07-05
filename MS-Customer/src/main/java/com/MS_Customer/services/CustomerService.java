package com.MS_Customer.services;

import com.MS_Customer.dto.CustomerDTO;
import com.MS_Customer.repositories.CustomerRepository;
import com.MS_Customer.services.mapping.CustomerMapper;
import com.MS_Customer.services.mapping.CustomersDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final CustomersDTOMapper customersDTOMapper;

    public CustomerDTO createCustomer(CustomerDTO customerDTO) throws IllegalArgumentException {
        if (customerDTO.getFirstName().isEmpty() ||
                customerDTO.getLastName().isEmpty() ||
                customerDTO.getSex() == null ||
                customerDTO.getCpf().isEmpty() ||
                customerDTO.getBirthdate() == null ||
                customerDTO.getEmail().isEmpty() ||
                customerDTO.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Um ou mais campos obrigatórios estão vazios ou nulos.");
        } else {
            var customer = customerMapper.createCustomer(customerDTO);
            return customersDTOMapper.createCustomerDTO(customerRepository.save(customer));
        }
    }
}
