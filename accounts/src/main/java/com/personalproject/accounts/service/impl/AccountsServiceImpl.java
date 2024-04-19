package com.personalproject.accounts.service.impl;

import com.personalproject.accounts.constant.AccountsConstants;
import com.personalproject.accounts.dto.AccountsDto;
import com.personalproject.accounts.dto.CustomerDto;
import com.personalproject.accounts.entity.Accounts;
import com.personalproject.accounts.entity.Customer;
import com.personalproject.accounts.exception.CustomerAlreadyExistsException;
import com.personalproject.accounts.exception.ResourceNotFoundException;
import com.personalproject.accounts.mapper.AccountsMapper;
import com.personalproject.accounts.mapper.CustomerMapper;
import com.personalproject.accounts.repository.AccountsRepository;
import com.personalproject.accounts.repository.CustomerRepository;
import com.personalproject.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    //autowired using constructor
    AccountsRepository accountsRepository;
    CustomerRepository customerRepository;

    /**
     * @param customerDto
     */
    @Override
    public void createAccount(CustomerDto customerDto) {

        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> optionCustomer =  customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if(optionCustomer.isPresent()){
            throw new CustomerAlreadyExistsException("Customer Already registered with given Number "+customerDto.getMobileNumber());
        }
        customer.setCreatedBy("Rajesh");
        customer.setCreatedAt(LocalDateTime.now());
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createAccounts(savedCustomer));
    }

    /**
     *
     * @param customer
     * @return Accounts
     */
    private Accounts createAccounts(Customer customer){
        Accounts accounts = new Accounts();

        accounts.setCustomerId(customer.getCustomerId());
        Long accountNumber = 1000000000L + new Random().nextInt(900000000);

        accounts.setAccountNumber(accountNumber);
        accounts.setAccountType(AccountsConstants.SAVINGS);
        accounts.setBranchAddress(AccountsConstants.ADDRESS);
        accounts.setCreatedBy("Rajesh");
        accounts.setCreatedAt(LocalDateTime.now());
        return accounts;
    }

    /**
     *
     * @param mobileNumber
     * @return
     */
    @Override
    public CustomerDto fetch(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer","Mobile Number",mobileNumber)
        );

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Accounts", "customer id", customer.getCustomerId().toString())
        );
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        return customerDto;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if(accountsDto != null){
            Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString())
            );
            AccountsMapper.mapToAccounts(accountsDto, accounts);
            accountsRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerId", customerId.toString())
            );
            CustomerMapper.mapToCustomer(customerDto, customer);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return isUpdated;
    }

    /**
     * @param mobileNumber
     * @return
     */
    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "MobileNumber", mobileNumber)
        );
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());

        return true;
    }

}
