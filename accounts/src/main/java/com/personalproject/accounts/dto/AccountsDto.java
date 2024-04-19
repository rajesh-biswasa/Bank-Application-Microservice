package com.personalproject.accounts.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class AccountsDto {
    private Long accountNumber;

    private String accountType;

    private String branchAddress;
}
