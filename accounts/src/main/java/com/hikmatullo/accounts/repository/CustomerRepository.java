package com.hikmatullo.accounts.repository;

import com.hikmatullo.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findCustomerByEmail(String email);

    Optional<Customer> findByPhoneNumber(String phoneNumber);
}
