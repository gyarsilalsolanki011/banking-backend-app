package com.gyarsilalsolanki011.banking.dto;

import com.gyarsilalsolanki011.banking.enums.AdminRole;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminDto {
    private Long id;
    private String username;
    private String email;
    private AdminRole role;
}
