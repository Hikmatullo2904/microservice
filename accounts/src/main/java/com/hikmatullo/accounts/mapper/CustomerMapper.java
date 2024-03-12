package com.hikmatullo.accounts.mapper;


import com.hikmatullo.accounts.dto.CustomerDetailsDto;
import com.hikmatullo.accounts.dto.CustomerDto;
import com.hikmatullo.accounts.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer mapToCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setPhoneNumber(customerDto.getPhoneNumber());

        return customer;
    }

    public CustomerDto mapToCustomerDto(Customer customer) {
        CustomerDto dto = new CustomerDto();
        dto.setName(customer.getName());
        dto.setEmail(customer.getEmail());
        dto.setPhoneNumber(customer.getPhoneNumber());

        return dto;
    }

    public CustomerDetailsDto mapToCustomerDetailsDto(Customer customer) {
        CustomerDetailsDto customerDetailsDto = new CustomerDetailsDto();
        customerDetailsDto.setName(customer.getName());
        customerDetailsDto.setEmail(customer.getEmail());
        customerDetailsDto.setPhoneNumber(customer.getPhoneNumber());
        return customerDetailsDto;
    }




}
