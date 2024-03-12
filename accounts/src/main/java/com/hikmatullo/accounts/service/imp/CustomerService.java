package com.hikmatullo.accounts.service.imp;

import com.hikmatullo.accounts.dto.CardsDto;
import com.hikmatullo.accounts.dto.CustomerDetailsDto;
import com.hikmatullo.accounts.dto.LoansDto;
import com.hikmatullo.accounts.entity.Account;
import com.hikmatullo.accounts.entity.Customer;
import com.hikmatullo.accounts.exception.CustomNotFoundException;
import com.hikmatullo.accounts.mapper.AccountMapper;
import com.hikmatullo.accounts.mapper.CustomerMapper;
import com.hikmatullo.accounts.repository.AccountRepository;
import com.hikmatullo.accounts.repository.CustomerRepository;
import com.hikmatullo.accounts.service.ICustomerService;
import com.hikmatullo.accounts.service.client.CardsFeignClient;
import com.hikmatullo.accounts.service.client.LoansFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService {
    private final CustomerRepository customerRepository;
    private final LoansFeignClient loansFeignClient;
    private final CardsFeignClient cardsFeignClient;
    private final AccountRepository accountRepository;
    private final CustomerMapper customerMapper;
    private final AccountMapper accountMapper;
    @Override
    public CustomerDetailsDto fetchCustomerDetails(String phoneNumber) {
        Customer customer = customerRepository.findByPhoneNumber(phoneNumber).orElseThrow((
        ) -> new CustomNotFoundException("Customer not found"));
        Account account = accountRepository.findAccountByCustomerId(customer.getId()).orElseThrow(
                () -> new CustomNotFoundException("Account not found"));
        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchCardDetails(phoneNumber);
        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(phoneNumber);
        CustomerDetailsDto dto = customerMapper.mapToCustomerDetailsDto(customer);
        dto.setLoansDto(loansDtoResponseEntity.getBody());
        dto.setCardsDto(cardsDtoResponseEntity.getBody());
        dto.setAccountDto(accountMapper.mapToAccountDto(account));

        return dto;
    }
}
