package com.gyarsilalsolanki011.banking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class AccountDto {
    private Long id;
    private String accountHolderName;
    private Double balance;
}
