package com.gyarsilalsolanki011.banking.mapper;

import com.gyarsilalsolanki011.banking.models.dto.TransactionDto;
import com.gyarsilalsolanki011.banking.models.entity.Transaction;

public class TransactionMapper {
    public static TransactionDto mapToTransactionDto(Transaction transaction) {
        return new TransactionDto(
                transaction.getId(),
                transaction.getAccount().getAccountNumber(),
                transaction.getToAccountNumber(),
                transaction.getTransactionType(),
                transaction.getAmount(),
                transaction.getTransactionDate(),
                transaction.getStatus(),
                transaction.getAccount().getBalance()
        );
    }
}
