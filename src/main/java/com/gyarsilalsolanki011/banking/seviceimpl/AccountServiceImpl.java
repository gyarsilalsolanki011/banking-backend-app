package com.gyarsilalsolanki011.banking.seviceimpl;

import com.gyarsilalsolanki011.banking.dto.AccountDto;
import com.gyarsilalsolanki011.banking.entity.Account;
import com.gyarsilalsolanki011.banking.entity.User;
import com.gyarsilalsolanki011.banking.enums.AccountType;
import com.gyarsilalsolanki011.banking.mapper.AccountMapper;
import com.gyarsilalsolanki011.banking.repository.AccountRepository;
import com.gyarsilalsolanki011.banking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public AccountDto createAccount(User user, AccountType accountType, Double balance) {
        String accountNumber = generateAccountNumber();
        Account newAccount = new Account(user, accountNumber, accountType, balance);
        Account savedAccount = accountRepository.save(newAccount);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public Optional<AccountDto> getAccountById(Long id) {
        return accountRepository.findById(id)
                .map(AccountMapper::mapToAccountDto);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        return accountRepository.findAll()
                .stream()
                .map(AccountMapper::mapToAccountDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public AccountDto deposit(Long accountId, double amount) {
        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        if (optionalAccount.isEmpty()){
            throw new IllegalArgumentException("Account not found");
        }

        Account account = optionalAccount.get();
        account.setBalance(account.getBalance() + amount); //update balance
        Account updateAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(updateAccount);
    }

    @Override
    public AccountDto withdraw(Long accountId, double amount) {
        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        if (optionalAccount.isEmpty()){
            throw new IllegalArgumentException("Account Not found");
        }

        Account account = optionalAccount.get();

        // Prevent overdraft
        if (account.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient balance");
        }

        account.setBalance(account.getBalance() - amount); //Deduct balance
        Account updateAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(updateAccount);
    }

    @Override
    public void delete(Long id) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if (optionalAccount.isEmpty()){
            throw new IllegalArgumentException("Account Not found");
        }

        Account account = optionalAccount.get();
        accountRepository.delete(account);
    }

    @Override
    public List<AccountDto> getAllAccountsByUserId(Long id) {
        List<Account> allAccounts = accountRepository.findByUserId(id);
        List<AccountDto> accounts = new ArrayList<>();

        if (allAccounts.isEmpty()){
            for (Account account : allAccounts){
                accounts.add(AccountMapper.mapToAccountDto(account));
            }
        }
        return accounts;
    }

    private String generateAccountNumber() {
        Random random = new Random();
        StringBuilder accountNumber = new StringBuilder();
        for(int i=0; i<12; i++){
            accountNumber.append(random.nextInt(10));
        }
        return accountNumber.toString();
    }
}
