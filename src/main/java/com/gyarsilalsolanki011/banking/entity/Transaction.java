package com.gyarsilalsolanki011.banking.entity;

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
    @Column(name = "transaction_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type")
    private TransactionType transactionType;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "date")
    private Date date = new Date();

    public Transaction(Account account, TransactionType transactionType, Double amount) {
        this.account = account;
        this.transactionType = transactionType;
        this.amount = amount;
    }
}
