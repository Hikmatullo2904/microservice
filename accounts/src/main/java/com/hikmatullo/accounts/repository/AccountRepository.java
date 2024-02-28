package com.hikmatullo.accounts.repository;

import com.hikmatullo.accounts.entity.Account;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    void deleteByCustomerId(Long id);
    Optional<Account> findAccountByCustomerId(Long customerId);
}
