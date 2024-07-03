package com.MS_Customer.services;

import com.MS_Customer.dto.CustomerDTO;
import com.MS_Customer.entities.Customer;
import com.MS_Customer.repositories.CustomerRepository;
import com.MS_Customer.services.mapping.CustomerMapper;
import com.MS_Customer.services.mapping.CustomerDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final CustomerDTOMapper customersDTOMapper;

    public ResponseEntity<CustomerDTO> createCustomer(CustomerDTO customerDTO) throws IllegalArgumentException {

        if (customerRepository.findByEmail(customerDTO.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(customerDTO.getPassword());
        customerDTO.setPassword(encryptedPassword);

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
            return ResponseEntity.ok(customersDTOMapper.createCustomerDTO(customerRepository.save(customer)));
        }
    }

    public ResponseEntity<CustomerDTO> getCustomer(Long id) {
        var customer = customerRepository.getReferenceById(id);
        return ResponseEntity.ok(customersDTOMapper.createCustomerDTO(customer));
    }
}
