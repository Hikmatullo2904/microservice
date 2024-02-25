package com.hikmatullo.accounts.service;

import com.hikmatullo.app.dto.CustomerDto;

public interface IAccountService {
    void save(CustomerDto dto);

    CustomerDto fetchAccount(String mobileNumber);

    boolean updateAccount(CustomerDto customerDto);

    boolean deleteAccount(String mobileNumber);
}
