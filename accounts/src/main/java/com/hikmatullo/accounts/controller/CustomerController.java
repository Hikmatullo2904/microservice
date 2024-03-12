package com.hikmatullo.accounts.controller;

import com.hikmatullo.accounts.dto.CustomerDetailsDto;
import com.hikmatullo.accounts.service.ICustomerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Customer" , description = "Customer controller to fetch customer details")
@RequiredArgsConstructor
@RequestMapping("/api")
public class CustomerController {

    private final ICustomerService customerService;

    @GetMapping("/customer-details")
    public CustomerDetailsDto fetchCustomerDetails(@RequestParam  String phoneNumber) {
        return customerService.fetchCustomerDetails(phoneNumber);
    }
}
