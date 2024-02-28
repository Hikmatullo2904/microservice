package com.hikmatullo.accounts.service.imp;

import com.hikmatullo.accounts.dto.AccountDto;
import com.hikmatullo.accounts.dto.CustomerDto;
import com.hikmatullo.accounts.entity.Account;
import com.hikmatullo.accounts.entity.Customer;
import com.hikmatullo.accounts.exception.CustomAlreadyException;
import com.hikmatullo.accounts.exception.CustomNotFoundException;
import com.hikmatullo.accounts.mapper.AccountMapper;
import com.hikmatullo.accounts.mapper.CustomerMapper;
import com.hikmatullo.accounts.repository.AccountRepository;
import com.hikmatullo.accounts.repository.CustomerRepository;
import com.hikmatullo.accounts.service.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AccountService implements IAccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final AccountMapper accountMapper;
    private final CustomerMapper customerMapper;
    @Override
    public void save(CustomerDto dto) {
        Optional<Customer> customerByEmail = customerRepository.findCustomerByEmail(dto.getEmail());
        if(customerByEmail.isPresent())
            throw new CustomAlreadyException("there is a customer registered with this email");
        Optional<Customer> byPhoneNumber = customerRepository.findByPhoneNumber(dto.getPhoneNumber());
        if(byPhoneNumber.isPresent())
            throw new CustomAlreadyException("there is a customer registered with this phone number");
        Customer customer = customerMapper.mapToCustomer(dto);
        customer = customerRepository.save(customer);
        Account newAccount = createNewAccount(customer);
        accountRepository.save(newAccount);
    }

    private Account createNewAccount(Customer customer) {
        Account account = new Account();
        account.setCustomerId(customer.getId());
        long randomNum = 1000L * new Random().nextInt(900_000_000);
        account.setAccountNumber(randomNum);
        account.setAccountType("Savings");
        account.setBranchAddress("Toshkent, sergili");
        return account;
    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByPhoneNumber(mobileNumber).orElseThrow(
                () -> new CustomNotFoundException("Customer not found")
        );
        Account accounts = accountRepository.findAccountByCustomerId(customer.getId()).orElseThrow(
                () -> new CustomNotFoundException("Account not found")
        );
        CustomerDto customerDto = customerMapper.mapToCustomerDto(customer);
        customerDto.setAccountDto(accountMapper.mapToAccountDto(accounts));
        return customerDto;
    }


    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountDto accountsDto = customerDto.getAccountDto();
        if(accountsDto !=null ){
            Account accounts = accountRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new CustomNotFoundException("Account")
            );
            accountMapper.mapToAccount(accountsDto);
            accounts = accountRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new CustomNotFoundException("Customer")
            );
            customerMapper.mapToCustomer(customerDto);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return  isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByPhoneNumber(mobileNumber).orElseThrow(
                () -> new CustomNotFoundException("Customer")
        );
        accountRepository.deleteByCustomerId(customer.getId());
        customerRepository.deleteById(customer.getId());
        return true;
    }
}
