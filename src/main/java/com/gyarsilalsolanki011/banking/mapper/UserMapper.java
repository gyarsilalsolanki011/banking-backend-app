package com.gyarsilalsolanki011.banking.mapper;

import com.gyarsilalsolanki011.banking.dto.UserDto;
import com.gyarsilalsolanki011.banking.entity.User;

public class UserMapper {
    // Convert User Entity to UserDto
    public static UserDto mapToUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getAddress(),
                user.getCreatedAt()
        );
    }

    // Convert UserDto to User Entity
    public static User mapToUser(UserDto userDto) {
       return new User(
               userDto.getName(),
               userDto.getEmail(),
               userDto.getPhone(),
               userDto.getAddress()
       );
    }
}
