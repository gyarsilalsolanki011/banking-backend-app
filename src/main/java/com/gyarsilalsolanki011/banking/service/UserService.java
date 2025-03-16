package com.gyarsilalsolanki011.banking.service;

import com.gyarsilalsolanki011.banking.dto.UserDto;
import com.gyarsilalsolanki011.banking.entity.User;

import java.util.List;

public interface UserService {
    UserDto createUser(String name, String email, String phone, String address);
    User getUserById(Long userId);
    List<UserDto> getAllUsers();
    void deleteUser(Long userId);
}
