package com.gyarsilalsolanki011.banking.mapper;

import com.gyarsilalsolanki011.banking.models.dto.UserDto;
import com.gyarsilalsolanki011.banking.models.entity.User;

public class UserMapper {
    public static UserDto mapToUserDto(User user) {
        return new UserDto(
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getAddress(),
                user.getOnlineBankingStatus()
        );
    }
}
