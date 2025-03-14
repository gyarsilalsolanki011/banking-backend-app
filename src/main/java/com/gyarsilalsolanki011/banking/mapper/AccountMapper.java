package com.gyarsilalsolanki011.banking.mapper;

import com.gyarsilalsolanki011.banking.dto.AccountDto;
import com.gyarsilalsolanki011.banking.entity.Account;
import com.gyarsilalsolanki011.banking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountMapper {
   @Autowired
   private static UserRepository userRepository;

    public static Account mapToAccount(AccountDto accountDto){
       return new Account(
               userRepository.findByName(accountDto.getAccountHolderName()),
               accountDto.getAccountNumber(),
               accountDto.getAccountType(),
               accountDto.getBalance()
       );
    }

    public static AccountDto mapToAccountDto(Account account){
       return new AccountDto(
               account.getUser().getName(),
               account.getAccountNumber(),
               account.getAccountType(),
               account.getBalance()
       );
    }
}
