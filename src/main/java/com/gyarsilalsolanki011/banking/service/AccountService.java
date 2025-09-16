package com.gyarsilalsolanki011.banking.service;

import com.gyarsilalsolanki011.banking.models.dto.AccountDto;
import com.gyarsilalsolanki011.banking.models.entity.User;
import com.gyarsilalsolanki011.banking.models.enums.AccountType;

import java.util.List;

public interface AccountService {
    AccountDto createAccount(User user, AccountType accountType, Double balance);
    AccountDto getAccountById(Long id);
    List<AccountDto> getAllAccounts();
    void delete(Long id);

    List<AccountDto> getAllAccountsByUserId(Long id);
}
