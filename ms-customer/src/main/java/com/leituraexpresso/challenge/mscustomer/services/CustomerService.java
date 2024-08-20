package com.leituraexpresso.challenge.mscustomer.services;

import com.leituraexpresso.challenge.mscustomer.dto.requests.CustomerRequestDTO;
import com.leituraexpresso.challenge.mscustomer.exceptions.customExceptions.*;
import com.leituraexpresso.challenge.mscustomer.repositories.CustomerRepository;
import com.leituraexpresso.challenge.mscustomer.services.mapping.CustomerMapper;
import com.leituraexpresso.challenge.mscustomer.dto.CustomerDTO;
import com.leituraexpresso.challenge.mscustomer.entities.Customer;
import com.leituraexpresso.challenge.mscustomer.request.CustomerNewPasswordRequest;
import com.leituraexpresso.challenge.mscustomer.services.mapping.CustomerDTOMapper;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    public CustomerDTO createCustomer(CustomerRequestDTO customerDTO) {
        checkEmail(customerDTO.email());
        checkCPF(customerDTO.cpf());

        String encryptedPassword = new BCryptPasswordEncoder().encode(customerDTO.password());

        var customer1 = new CustomerDTO(customerDTO, encryptedPassword);

        return new CustomerDTO(saveCustomer(customer1));
    }

    private Customer saveCustomer(CustomerDTO customerDTO){
        return customerRepository.save(new Customer(customerDTO));
    }

    private void checkEmail(String email){
        if (customerRepository.findByEmail(email).isPresent())
            throw new EmailAddressAlreadyExistsException();
    }

    private void checkCPF(String cpf){
        if (customerRepository.findByCpf(cpf).isPresent())
            throw new CPFAlreadyInUseException();
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

    public void updatePassword(
        Long idCustomer,
        UserDetails customerAuthenticated,
        CustomerNewPasswordRequest newPasswordDTO
    ){
        changePasswordFromCustomer(idCustomer, customerAuthenticated, newPasswordDTO);
    }

    private void changePasswordFromCustomer(
        Long idCustomer,
        UserDetails customerAuthenticated,
        CustomerNewPasswordRequest newPasswordDTO
    ){
        CustomerDTO customerAuth = getCustomerByEmail(customerAuthenticated.getUsername());
        CustomerDTO customerDTO = getCustomerByIdAuth(idCustomer);

        checkIfItIsSameCustomer(customerAuth, customerDTO);

        String encode = new BCryptPasswordEncoder().encode(newPasswordDTO.toString());

        customerAuth.setPassword(encode);

        customerRepository.save(new Customer(customerAuth, returnEntityByEmail(customerAuth.getEmail())));
    }

    private void checkIfItIsSameCustomer(CustomerDTO customerAuth, CustomerDTO customerDTO){
        if (!customerAuth.getEmail().equals(customerDTO.getEmail()))
            throw new NotAllowedToChangePasswordFromOtherCustomerException();
    }

    private CustomerDTO getCustomerByEmail(String email){
        var customer = returnEntityByEmail(email);

        return new CustomerDTO(customer);
    }

    private Customer returnEntityByEmail(String email){
        return customerRepository.findByEmail(email).orElseThrow(CustomerNotFoundException::new);
    }

    private CustomerDTO getCustomerByIdAuth(Long id){
        var customer = returnEntity(id);

        return new CustomerDTO(customer);
    }

    private Customer returnEntity(Long id){
        return customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new);
    }
}
