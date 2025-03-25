package com.gyarsilalsolanki011.banking.service;

import com.gyarsilalsolanki011.banking.dto.UserDto;
import com.gyarsilalsolanki011.banking.entity.User;
import com.gyarsilalsolanki011.banking.enums.OnlineBankingStatus;

import java.util.List;

public interface UserService {
    UserDto createUser(String name, String email, String phone, String address);
    String requestOnlineBanking(Long userId, String bankingPassword);
    OnlineBankingStatus getOnlineBankingStatus(Long userId, String authenticatedUsername);
    String updateUser(Long userId, String username, String email, String phone, String address, String authenticatedUsername);
    User getUserById(Long userId, String authenticatedUsername);
    void deleteUser(Long userId, String authenticatedUsername);
    List<UserDto> getAllUsers();
}
