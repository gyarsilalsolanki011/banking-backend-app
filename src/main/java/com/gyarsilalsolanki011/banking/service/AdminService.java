package com.gyarsilalsolanki011.banking.service;

import com.gyarsilalsolanki011.banking.dto.AdminDto;
import com.gyarsilalsolanki011.banking.enums.AdminRole;

import java.util.List;

public interface AdminService {
    AdminDto createAdmin(String username, String email, String password, AdminRole role);
    AdminDto getAdminById(Long adminId);
    List<AdminDto> getAllAdmins();
    void deleteAdmin(Long adminId);
}
