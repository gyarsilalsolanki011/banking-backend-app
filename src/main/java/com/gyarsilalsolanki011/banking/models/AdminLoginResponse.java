package com.gyarsilalsolanki011.banking.models;

import lombok.Data;

@Data
public class AdminLoginResponse {
    private String username;
    private String password;

    public AdminLoginResponse(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
