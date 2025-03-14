package com.gyarsilalsolanki011.banking.dto;

import com.gyarsilalsolanki011.banking.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class AccountDto {
    private String accountHolderName;
    private String accountNumber;
    private AccountType accountType;
    private Double balance;
}
