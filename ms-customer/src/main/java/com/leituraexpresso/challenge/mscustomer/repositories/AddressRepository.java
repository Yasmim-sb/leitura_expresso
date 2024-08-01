package com.leituraexpresso.challenge.mscustomer.repositories;

import com.leituraexpresso.challenge.mscustomer.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
