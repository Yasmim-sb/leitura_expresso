package com.MS_Customer.services;

import com.MS_Customer.dto.CustomerDTO;
import com.MS_Customer.repositories.CustomerRepository;
import com.MS_Customer.services.mapping.CustomerMapper;
import com.MS_Customer.services.mapping.CustomersDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final CustomersDTOMapper customersDTOMapper;

    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO){
        var customerExisting = customerRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Customer not found"));

        customerExisting.setFirstName(customerDTO.getFirstName());
        customerExisting.setLastName(customerDTO.getLastName());
        customerExisting.setCpf(customerDTO.getCpf());
        customerExisting.setBirthdate(customerDTO.getBirthdate());
        customerExisting.setEmail(customerDTO.getEmail());
        customerExisting.setPassword(customerDTO.getPassword());
        customerExisting.setSex(customerDTO.getSex());
        customerExisting.setActive(customerDTO.isActive());

        return customersDTOMapper.createCustomerDTO(customerRepository.save(customerExisting));
    }


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
    public ResponseEntity<CustomerDTO> getCustomer(Long id) {
        var customer = customerRepository.getReferenceById(id);
        return ResponseEntity.ok(customersDTOMapper.createCustomerDTO(customer));
    }
}
