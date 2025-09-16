package com.gyarsilalsolanki011.banking.models.dto;

import com.gyarsilalsolanki011.banking.models.enums.OnlineBankingStatus;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private String name;
    private String email;
    private String phone;
    private String address;
    private OnlineBankingStatus onlineBankingStatus;

    public UserDto(String name, String email, String phone, String address, OnlineBankingStatus onlineBankingStatus) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.onlineBankingStatus = onlineBankingStatus;
    }
}
