package com.personalproject.accounts.repository;

import com.personalproject.accounts.entity.Accounts;
import com.personalproject.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountsRepository extends JpaRepository<Accounts, Long> {
}