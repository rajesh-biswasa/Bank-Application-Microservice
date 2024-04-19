package com.personalproject.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class CustomerDto {
    private String name;
    private String email;
    private String mobileNumber;
    private AccountsDto accountsDto;
}
