package com.gyarsilalsolanki011.banking.dto;

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

    public UserDto(String name, String email, String phone, String address) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }
}
