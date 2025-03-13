package com.gyarsilalsolanki011.banking.service;

import com.gyarsilalsolanki011.banking.dto.UserDto;

public interface UserService {
    UserDto createUser(String name, String email, String phone, String Address);
    UserDto getUserById(Long id);
}
