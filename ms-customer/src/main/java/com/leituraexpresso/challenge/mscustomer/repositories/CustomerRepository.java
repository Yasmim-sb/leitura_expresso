package com.leituraexpresso.challenge.mscustomer.repositories;

import com.leituraexpresso.challenge.mscustomer.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    //UserDetails findByEmail(String email);
    Optional<Customer> findByEmail(String email);
    Optional<Customer> findByCpf(String cpf);

}
