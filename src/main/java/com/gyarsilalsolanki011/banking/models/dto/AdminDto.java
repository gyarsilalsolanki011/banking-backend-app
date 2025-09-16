package com.gyarsilalsolanki011.banking.models.dto;

import com.gyarsilalsolanki011.banking.models.enums.AdminRole;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
public class AdminDto {
    private Long id;
    private String username;
    private String email;
    private AdminRole role;

    public AdminDto(Long id, String username, String email, AdminRole role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
    }
}
