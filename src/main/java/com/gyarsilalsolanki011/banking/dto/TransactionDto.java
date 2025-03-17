package com.gyarsilalsolanki011.banking.dto;

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
    private TransactionType transactionType;
    private Double amount;
    private Date date;

    public TransactionDto(Long transactionId, String accountNumber, TransactionType transactionType, Double amount, Date date) {
        this.transactionId = transactionId;
        this.accountNumber = accountNumber;
        this.transactionType = transactionType;
        this.amount = amount;
        this.date = date;
    }
}
