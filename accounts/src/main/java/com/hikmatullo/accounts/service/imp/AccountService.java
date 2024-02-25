package com.hikmatullo.accounts.service.imp;

import com.hikmatullo.app.dto.AccountDto;
import com.hikmatullo.app.dto.CustomerDto;
import com.hikmatullo.app.entity.Account;
import com.hikmatullo.app.entity.Customer;
import com.hikmatullo.app.exception.CustomAlreadyException;
import com.hikmatullo.app.exception.CustomNotFoundException;
import com.hikmatullo.app.mapper.AccountMapper;
import com.hikmatullo.app.mapper.CustomerMapper;
import com.hikmatullo.app.repository.AccountRepository;
import com.hikmatullo.app.repository.CustomerRepository;
import com.hikmatullo.app.service.IAccountService;
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
        Account accounts = accountRepository.findById(customer.getId()).orElseThrow(
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
