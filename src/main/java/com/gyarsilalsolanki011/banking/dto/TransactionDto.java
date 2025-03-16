package com.gyarsilalsolanki011.banking.dto;

import com.gyarsilalsolanki011.banking.enums.TransactionType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {
    private Long transactionId;
    private String accountNumber;
    private TransactionType transactionType;
    private Double amount;
    private Date date;
}
