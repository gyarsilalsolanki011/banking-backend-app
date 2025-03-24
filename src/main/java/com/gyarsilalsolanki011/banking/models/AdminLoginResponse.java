package com.gyarsilalsolanki011.banking.models;

import lombok.Data;

@Data
public class AdminLoginResponse {
    private String token;
    private String role;

    public AdminLoginResponse(String token, String role) {
        this.token = token;
        this.role = role;
    }
}
