package com.gyarsilalsolanki011.banking.service;

import com.gyarsilalsolanki011.banking.dto.UserDto;
import com.gyarsilalsolanki011.banking.entity.User;
import com.gyarsilalsolanki011.banking.enums.OnlineBankingStatus;

import java.util.List;

public interface UserService {
    UserDto createUser(String name, String email, String phone, String address);
    String requestOnlineBanking(String email, String bankingPassword);
    String getOnlineBankingStatus(String email, String authenticatedUsername);
    String updateUser(String originalEmail, String username, String email, String phone, String address, String authenticatedUsername);
    User getUserById(Long userId, String authenticatedUsername);
    void deleteUser(String email, String authenticatedUsername);
    List<UserDto> getAllUsers();
}
