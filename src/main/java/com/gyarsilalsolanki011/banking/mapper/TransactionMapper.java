package com.gyarsilalsolanki011.banking.mapper;

import com.gyarsilalsolanki011.banking.dto.TransactionDto;
import com.gyarsilalsolanki011.banking.entity.Transaction;
import com.gyarsilalsolanki011.banking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;

public class TransactionMapper {
    public static TransactionDto mapToTransactionDto(Transaction transaction) {
        return new TransactionDto(
                transaction.getId(),
                transaction.getAccount().getAccountNumber(),
                transaction.getToAccountNumber(),
                transaction.getTransactionType(),
                transaction.getAmount(),
                transaction.getDate(),
                transaction.getStatus(),
                transaction.getAccount().getBalance()
        );
    }
}
