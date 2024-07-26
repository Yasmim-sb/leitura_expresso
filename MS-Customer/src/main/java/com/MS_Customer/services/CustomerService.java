package com.MS_Customer.services;

import com.MS_Customer.dto.CustomerDTO;
import com.MS_Customer.entities.Customer;
import com.MS_Customer.exceptions.customExceptions.CustomerNotFound;
import com.MS_Customer.exceptions.customExceptions.NotAllowedException;
import com.MS_Customer.repositories.CustomerRepository;
import com.MS_Customer.request.CustomerNewPasswordRequest;
import com.MS_Customer.services.mapping.CustomerDTOMapper;
import com.MS_Customer.services.mapping.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final AddressService addressService;
    private final CustomerDTOMapper customersDTOMapper;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<CustomerDTO> createCustomer(CustomerDTO customerDTO) throws IllegalArgumentException {

        if (customerRepository.findByEmail(customerDTO.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = passwordEncoder.encode(customerDTO.getPassword());
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
            customerRepository.save(customerMapper.createCustomer(customerDTO));

            var response = customersDTOMapper.createCustomerDTO(customer);
            return ResponseEntity.ok(response);
        }
    }

    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO){
        var customerExisting = customerRepository.findById(id)
        .orElseThrow(CustomerNotFound::new);

        if (customerDTO.getPassword() != null){
            throw new NotAllowedException();
        }
        NullBeanUtils.copyNonNullProperties(customerDTO, customerExisting);

        return customersDTOMapper.createCustomerDTO(customerRepository.save(customerExisting));
    }


    public ResponseEntity<CustomerDTO> getCustomer(Long id) {
        var customer = customerRepository.getReferenceById(id);
        return ResponseEntity.ok(customersDTOMapper.createCustomerDTO(customer));
    }

    public CustomerDTO getCustomerById(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
        .orElseThrow(CustomerNotFound::new);

        return addressService.convertToCustomerDTO(customer);
    }

    public void updatePassword(Long id, CustomerNewPasswordRequest newPasswordDTO){
        changePasswordFromCustomer(getCustomerById(id), newPasswordDTO);
    }

    private void changePasswordFromCustomer(Customer customer, CustomerNewPasswordRequest newPasswordDTO){
        customer.setPassword(newPasswordDTO.getNewPassword());
        customerRepository.save(customer);
    }

}
