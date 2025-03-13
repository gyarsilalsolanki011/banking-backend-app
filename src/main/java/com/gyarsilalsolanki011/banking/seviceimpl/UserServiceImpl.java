package com.gyarsilalsolanki011.banking.seviceimpl;

import com.gyarsilalsolanki011.banking.dto.UserDto;
import com.gyarsilalsolanki011.banking.entity.User;
import com.gyarsilalsolanki011.banking.mapper.UserMapper;
import com.gyarsilalsolanki011.banking.repository.UserRepository;
import com.gyarsilalsolanki011.banking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto createUser(String name, String email, String phone, String address) {
        User newUser = new User(name, email, phone, address);
        User savedUser = userRepository.save(newUser);
        return UserMapper.mapToUserDto(savedUser);
    }

    @Override
    public Optional<User> getUserById(Long id) {
       return userRepository.findById(id);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::mapToUserDto)
                .collect(Collectors.toList());
    }
}
