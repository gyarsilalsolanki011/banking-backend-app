package com.gyarsilalsolanki011.banking.seviceimpl;

import com.gyarsilalsolanki011.banking.dto.TransactionDto;
import com.gyarsilalsolanki011.banking.entity.Account;
import com.gyarsilalsolanki011.banking.entity.Transaction;
import com.gyarsilalsolanki011.banking.enums.TransactionType;
import com.gyarsilalsolanki011.banking.mapper.TransactionMapper;
import com.gyarsilalsolanki011.banking.repository.AccountRepository;
import com.gyarsilalsolanki011.banking.repository.TransactionRepository;
import com.gyarsilalsolanki011.banking.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    @Transactional
    public TransactionDto deposit(Long accountId, double amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);

        Transaction transaction = new Transaction(account, TransactionType.DEPOSIT, amount);
        transactionRepository.save(transaction);

        return TransactionMapper.mapToTransactionDto(transaction);
    }

    @Override
    @Transactional
    public TransactionDto withdraw(Long accountId, double amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if (account.getBalance() < 0 || account.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);

        Transaction transaction = new Transaction(account, TransactionType.WITHDRAWAL, amount);
        transactionRepository.save(transaction);
        return TransactionMapper.mapToTransactionDto(transaction);
    }

    @Override
    public List<TransactionDto> getTransactionsByAccountId(Long accountId) {
        List<Transaction> transactions = transactionRepository.findByAccountId(accountId);
        return transactions.stream()
                .map(TransactionMapper::mapToTransactionDto)
                .collect(Collectors.toList());
    }
}
