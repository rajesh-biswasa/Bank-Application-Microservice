package com.personalproject.accounts.service.impl;

import com.personalproject.accounts.dto.CustomerDto;
import com.personalproject.accounts.repository.AccountsRepository;
import com.personalproject.accounts.repository.CustomerRepository;
import com.personalproject.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    AccountsRepository accountsRepository;
    CustomerRepository customerRepository;

    /**
     * @param customerDto
     */
    @Override
    public void createAccount(CustomerDto customerDto) {

    }
}
