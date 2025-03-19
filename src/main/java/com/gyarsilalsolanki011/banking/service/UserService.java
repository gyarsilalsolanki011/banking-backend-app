package com.gyarsilalsolanki011.banking.service;

import com.gyarsilalsolanki011.banking.dto.UserDto;
import com.gyarsilalsolanki011.banking.entity.User;
import com.gyarsilalsolanki011.banking.enums.OnlineBankingStatus;

import java.util.List;

public interface UserService {
    UserDto createUser(String name, String email, String phone, String address);
    OnlineBankingStatus getOnlineBankingStatus(Long userId);
    String requestOnlineBanking(Long userId, String bankingPassword);
    String updateUser(Long userId, String username, String email, String phone, String address);
    User getUserById(Long userId);

    List<UserDto> getAllUsers();
    void deleteUser(Long userId);
}
