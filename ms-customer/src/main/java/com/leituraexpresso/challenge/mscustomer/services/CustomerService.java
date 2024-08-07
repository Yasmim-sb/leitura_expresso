package com.leituraexpresso.challenge.mscustomer.services;

import com.leituraexpresso.challenge.mscustomer.exceptions.customExceptions.ConflictException;
import com.leituraexpresso.challenge.mscustomer.repositories.CustomerRepository;
import com.leituraexpresso.challenge.mscustomer.services.mapping.CustomerMapper;
import com.leituraexpresso.challenge.mscustomer.dto.CustomerDTO;
import com.leituraexpresso.challenge.mscustomer.entities.Customer;
import com.leituraexpresso.challenge.mscustomer.exceptions.customExceptions.NotAllowedException;
import com.leituraexpresso.challenge.mscustomer.request.CustomerNewPasswordRequest;
import com.leituraexpresso.challenge.mscustomer.services.mapping.CustomerDTOMapper;
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
    private final AddressService addressService;
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
        .orElseThrow(NotAllowedException::new);

        if (customerDTO.getPassword() != null){
            throw new NotAllowedException();
        }
        NullBeanUtils.copyNonNullProperties(customerDTO, customerExisting);

        return customersDTOMapper.createCustomerDTO(customerRepository.save(customerExisting));
    }


    public CustomerDTO getCustomer(Long id) {
        var customer = customerRepository.getReferenceById(id);
        return customersDTOMapper.createCustomerDTO(customer);
    }


    //Comentei pois são endpoints que serão criados ou mexidos posteriormente
//    public void updatePassword(Long id, CustomerNewPasswordRequest newPasswordDTO){
//        changePasswordFromCustomer(getCustomerById(id), newPasswordDTO);
//    }
//
//    private Customer getCustomerById(Long id) {
//        return customerRepository.findById(id).orElseThrow(NotAllowedExceptionException::new);
//    }


    private void changePasswordFromCustomer(Customer customer, CustomerNewPasswordRequest newPasswordDTO){
        customer.setPassword(newPasswordDTO.getNewPassword());
        customerRepository.save(customer);
    }

}
