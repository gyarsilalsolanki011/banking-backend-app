package com.gyarsilalsolanki011.banking.service;

import com.gyarsilalsolanki011.banking.models.dto.TransactionDto;

import java.util.List;

public interface TransactionService {
    TransactionDto deposit(Long accountId, double amount);
    TransactionDto withdraw(Long accountId, double amount);
    TransactionDto transfer(Long fromAccountId, String toAccountNumber, double amount);
    List<TransactionDto> getTransactionsByAccountId(Long accountId);
}
