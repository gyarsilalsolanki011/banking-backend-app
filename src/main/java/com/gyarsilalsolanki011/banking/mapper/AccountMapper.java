package com.gyarsilalsolanki011.banking.mapper;

import com.gyarsilalsolanki011.banking.dto.AccountDto;
import com.gyarsilalsolanki011.banking.entity.Account;

public class AccountMapper {
    public static Account mapToAccount(AccountDto accountDto){
       return new Account(
               accountDto.getUser(),
               accountDto.getAccountNumber(),
               accountDto.getAccountType(),
               accountDto.getBalance()
       );
    }

    public static AccountDto mapToAccountDto(Account account){
       return new AccountDto(
               account.getUser(),
               account.getAccountNumber(),
               account.getAccountType(),
               account.getBalance()
       );
    }
}
