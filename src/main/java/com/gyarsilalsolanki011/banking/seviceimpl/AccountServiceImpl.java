package com.gyarsilalsolanki011.banking.seviceimpl;

import com.gyarsilalsolanki011.banking.dto.AccountDto;
import com.gyarsilalsolanki011.banking.entity.Account;
import com.gyarsilalsolanki011.banking.entity.User;
import com.gyarsilalsolanki011.banking.enums.AccountType;
import com.gyarsilalsolanki011.banking.mapper.AccountMapper;
import com.gyarsilalsolanki011.banking.repository.AccountRepository;
import com.gyarsilalsolanki011.banking.repository.UserRepository;
import com.gyarsilalsolanki011.banking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public AccountDto createAccount(User user, AccountType accountType, Double balance) {
        String accountNumber = generateAccountNumber();
        Account newAccount = new Account(user, accountNumber, accountType, balance);
        Account savedAccount = accountRepository.save(newAccount);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
         Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account Not found"));
         return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        return accountRepository.findAll()
                .stream()
                .map(AccountMapper::mapToAccountDto)
                .collect(Collectors.toList());
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
    public List<AccountDto> getAllAccountsByUserId(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        List<Account> accounts = accountRepository.findByUserId(userId);
        if (optionalUser.isEmpty()){
            throw new IllegalArgumentException("User Not found");
        } else if (accounts.isEmpty()){
            throw new IllegalArgumentException("There is no account for this user");
        } else {
            return accountRepository.findByUserId(userId)
                    .stream()
                    .map(AccountMapper::mapToAccountDto)
                    .collect(Collectors.toList());
        }
    }

    private String generateAccountNumber() {
        Random random = new Random();
        StringBuilder accountNumber = new StringBuilder();
        accountNumber.append("9525000"); //fist 4 digit of account
        for(int i=0; i<5; i++){
            accountNumber.append(random.nextInt(10));
        }
        return accountNumber.toString();
    }
}
