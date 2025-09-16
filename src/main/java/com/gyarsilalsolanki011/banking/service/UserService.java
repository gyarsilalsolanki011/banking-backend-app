package com.gyarsilalsolanki011.banking.service;

import com.gyarsilalsolanki011.banking.models.dto.UserDto;
import com.gyarsilalsolanki011.banking.models.entity.User;

import java.util.List;

public interface UserService {
    UserDto createUser(String name, String email, String phone, String address);
    String requestOnlineBanking(String email, String bankingPassword);
    String getOnlineBankingStatus(String email, String authenticatedUsername);
    String updateUser(String originalEmail, String username, String email, String phone, String address, String authenticatedUsername);
    User getUserById(Long userId, String authenticatedUsername);
    String forgotPassword(String email, String password);
    void deleteUser(String email, String authenticatedUsername);
    List<UserDto> getAllUsers();
}
