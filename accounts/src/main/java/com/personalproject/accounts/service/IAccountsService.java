package com.personalproject.accounts.service;

import com.personalproject.accounts.dto.CustomerDto;

public interface IAccountsService {

    /**
     *
     * @param customerDto
     */
    public void createAccount(CustomerDto customerDto);
}
