package com.gyarsilalsolanki011.banking.models;

import lombok.Data;

@Data
public class StringResponse {
    String status;

    public StringResponse(String status) {
        this.status = status;
    }
}
