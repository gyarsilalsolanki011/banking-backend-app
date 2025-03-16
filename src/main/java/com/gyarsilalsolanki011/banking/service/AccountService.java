package com.gyarsilalsolanki011.banking.service;

import com.gyarsilalsolanki011.banking.dto.AccountDto;
import com.gyarsilalsolanki011.banking.entity.User;
import com.gyarsilalsolanki011.banking.enums.AccountType;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    AccountDto createAccount(User user, AccountType accountType, Double balance);
    AccountDto getAccountById(Long id);
    List<AccountDto> getAllAccounts();
    void delete(Long id);

    // Using userId
    List<AccountDto> getAllAccountsByUserId(Long id);
}
