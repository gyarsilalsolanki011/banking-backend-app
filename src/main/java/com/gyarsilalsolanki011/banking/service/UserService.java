package com.gyarsilalsolanki011.banking.service;

import com.gyarsilalsolanki011.banking.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDto createUser(String name, String email, String phone, String address);
    Optional<UserDto> getUserById(Long id);
    List<UserDto> getAllUsers();
}
