package com.hikmatullo.accounts.service;

import com.hikmatullo.accounts.dto.CustomerDetailsDto;

public interface ICustomerService {

    CustomerDetailsDto fetchCustomerDetails(String phoneNumber);
}
