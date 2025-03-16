package com.gyarsilalsolanki011.banking.mapper;

import com.gyarsilalsolanki011.banking.dto.AdminDto;
import com.gyarsilalsolanki011.banking.entity.Admin;

public class AdminMapper {
    public static AdminDto mapToAdminDto(Admin admin) {
        return new AdminDto(
                admin.getId(),
                admin.getUsername(),
                admin.getEmail(),
                admin.getRole()
        );
    }
}
