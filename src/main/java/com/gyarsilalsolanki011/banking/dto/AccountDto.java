package com.gyarsilalsolanki011.banking.dto;

import com.gyarsilalsolanki011.banking.entity.User;
import com.gyarsilalsolanki011.banking.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
public class AccountDto {
    private Long id;
    private User user;
    private String accountNumber;
    private AccountType accountType;
    private Double balance;
    private Date createdAt;
}
