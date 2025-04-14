package com.gyarsilalsolanki011.banking.entity;

import com.gyarsilalsolanki011.banking.enums.TransactionStatus;
import com.gyarsilalsolanki011.banking.enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id", unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Column(name = "to_account_number")
    private String toAccountNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type")
    private TransactionType transactionType;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "date")
    private Date date = new Date();

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TransactionStatus status;

    public Transaction(Account account, String toAccountNumber, Double amount, TransactionType transactionType, TransactionStatus status) {
        this.account = account;
        this.toAccountNumber = toAccountNumber;
        this.transactionType = transactionType;
        this.amount = amount;
        this.status = status;
    }

}
