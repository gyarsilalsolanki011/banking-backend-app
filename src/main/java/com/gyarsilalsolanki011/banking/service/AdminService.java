package com.gyarsilalsolanki011.banking.service;

import com.gyarsilalsolanki011.banking.models.dto.AdminDto;
import com.gyarsilalsolanki011.banking.models.enums.AdminRole;

import java.util.List;

public interface AdminService {
    AdminDto createAdmin(String username, String email, String password, AdminRole role);
    String updateAdmin(Long adminId, String username, String email, String password, AdminRole role);
    AdminDto getAdminById(Long adminId);
    List<AdminDto> getAllAdmins();
    void deleteAdmin(Long adminId);
    void approveWithdrawal(Long transactionId);
    String approveOnlineBanking(Long userId);
    String deactivateOnlineBanking(Long userId);
}
