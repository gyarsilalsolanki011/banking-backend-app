package com.gyarsilalsolanki011.banking.service;

import com.gyarsilalsolanki011.banking.dto.AccountDto;

import java.util.List;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);
    AccountDto getAccountById(Long id);
    List<AccountDto> getAllAccounts();

    AccountDto deposit(Long id, double amount);
    AccountDto withdraw(Long id, double amount);

    void delete(Long id);
}
