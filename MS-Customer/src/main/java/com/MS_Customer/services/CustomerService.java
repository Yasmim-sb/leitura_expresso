package com.MS_Customer.services;

import com.MS_Customer.dto.CustomerDTO;
import com.MS_Customer.exceptions.customExceptions.ConflictException;
import com.MS_Customer.request.CustomerNewPasswordRequest;
import com.MS_Customer.entities.Customer;
import com.MS_Customer.exceptions.customExceptions.CustomerNotFound;
import com.MS_Customer.exceptions.customExceptions.CustomerNotFoundException;
import com.MS_Customer.exceptions.customExceptions.NotAllowedException;
import com.MS_Customer.repositories.CustomerRepository;
import com.MS_Customer.services.mapping.CustomerMapper;
import com.MS_Customer.services.mapping.CustomerDTOMapper;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final CustomerDTOMapper customersDTOMapper;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<CustomerDTO> createCustomer(CustomerDTO customerDTO) throws BadRequestException, ConflictException {
        if (customerDTO.getFirstName().isEmpty() ||
                customerDTO.getLastName().isEmpty() ||
                customerDTO.getSex() == null ||
                customerDTO.getCpf().isEmpty() ||
                customerDTO.getBirthdate() == null ||
                customerDTO.getEmail().isEmpty() ||
                customerDTO.getPassword().isEmpty()) {
            throw new BadRequestException("Um ou mais campos obrigatórios estão vazios ou nulos.");
        }

        Optional<Customer> existingCustomerByEmail = customerRepository.findByEmail(customerDTO.getEmail());
        if (existingCustomerByEmail.isPresent()) {
            throw new ConflictException("Email já em uso.");
        }

        Optional<Customer> existingCustomerByCpf = customerRepository.findByCpf(customerDTO.getCpf());
        if (existingCustomerByCpf.isPresent()) {
            throw new ConflictException("CPF já em uso.");
        }

        String encryptedPassword = passwordEncoder.encode(customerDTO.getPassword());
        customerDTO.setPassword(encryptedPassword);

        var customer = customerMapper.createCustomer(customerDTO);
        customerRepository.save(customer);

        var response = customersDTOMapper.createCustomerDTO(customer);
        return ResponseEntity.ok(response);
    }


    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO){
        var customerExisting = customerRepository.findById(id)
        .orElseThrow(CustomerNotFound::new);

        if (customerDTO.getPassword() != null){
            throw new NotAllowedException();
        }
        customerExisting.setFirstName(customerDTO.getFirstName());
        customerExisting.setLastName(customerDTO.getLastName());
        customerExisting.setCpf(customerDTO.getCpf());
        customerExisting.setBirthdate(customerDTO.getBirthdate());
        customerExisting.setEmail(customerDTO.getEmail());
        customerExisting.setSex(customerDTO.getSex());
        customerExisting.setActive(customerDTO.isActive());

        return customersDTOMapper.createCustomerDTO(customerRepository.save(customerExisting));
    }

    public ResponseEntity<CustomerDTO> getCustomer(Long id) {
        var customer = customerRepository.getReferenceById(id);
        return ResponseEntity.ok(customersDTOMapper.createCustomerDTO(customer));
    }

    public void updatePassword(Long id, CustomerNewPasswordRequest newPasswordDTO){
        changePasswordFromCustomer(getCustomerById(id), newPasswordDTO);
    }

    private Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new);
    }

    private void changePasswordFromCustomer(Customer customer, CustomerNewPasswordRequest newPasswordDTO){
        customer.setPassword(newPasswordDTO.getNewPassword());
        customerRepository.save(customer);
    }

}
