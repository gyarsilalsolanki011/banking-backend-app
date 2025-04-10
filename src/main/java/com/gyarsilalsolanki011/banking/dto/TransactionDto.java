package com.gyarsilalsolanki011.banking.dto;

import com.gyarsilalsolanki011.banking.enums.TransactionStatus;
import com.gyarsilalsolanki011.banking.enums.TransactionType;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
public class TransactionDto {
    private Long transactionId;
    private String accountNumber;
    private String toAccountNumber;
    private TransactionType transactionType;
    private Double amount;
    private Date date;
    private TransactionStatus transactionStatus;
    private Double amountRemained;

    public TransactionDto(Long transactionId, String accountNumber, String toAccountNumber, TransactionType transactionType, Double amount, Date date, TransactionStatus status, Double amountRemained) {
        this.transactionId = transactionId;
        this.accountNumber = accountNumber;
        this.toAccountNumber = toAccountNumber;
        this.transactionType = transactionType;
        this.amount = amount;
        this.date = date;
        this.transactionStatus = status;
        this.amountRemained = amountRemained;
    }
}
