package com.hikmatullo.accounts.repository;

import com.hikmatullo.app.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    void deleteByCustomerId(Long id);
}
