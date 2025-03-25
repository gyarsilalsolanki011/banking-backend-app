package com.gyarsilalsolanki011.banking.mapper;

import com.gyarsilalsolanki011.banking.dto.AccountDto;
import com.gyarsilalsolanki011.banking.entity.Account;

public class AccountMapper {
    public static AccountDto mapToAccountDto(Account account){
       return new AccountDto(
               account.getId(),
               account.getUser().getName(),
               account.getAccountNumber(),
               account.getAccountType(),
               account.getBalance()
       );
    }
}
