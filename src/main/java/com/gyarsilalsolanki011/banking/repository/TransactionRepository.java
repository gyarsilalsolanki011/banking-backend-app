package com.gyarsilalsolanki011.banking.repository;

import com.gyarsilalsolanki011.banking.models.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAccountId(Long accountId);
}
