package com.gyarsilalsolanki011.banking.dto;

import com.gyarsilalsolanki011.banking.enums.AccountType;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
public class AccountDto {
    private Long accountId;
    private String accountHolderName;
    private String accountNumber;
    private AccountType accountType;
    private Double balance;
    public AccountDto(Long accountId, String accountHolderName, String accountNumber, AccountType accountType, Double balance) {
        this.accountId = accountId;
        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
    }
}
