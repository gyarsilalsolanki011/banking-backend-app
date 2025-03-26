package com.gyarsilalsolanki011.banking.seviceimpl;

import com.gyarsilalsolanki011.banking.dto.UserDto;
import com.gyarsilalsolanki011.banking.entity.Account;
import com.gyarsilalsolanki011.banking.entity.User;
import com.gyarsilalsolanki011.banking.enums.OnlineBankingStatus;
import com.gyarsilalsolanki011.banking.mapper.UserMapper;
import com.gyarsilalsolanki011.banking.repository.AccountRepository;
import com.gyarsilalsolanki011.banking.repository.UserRepository;
import com.gyarsilalsolanki011.banking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDto createUser(String name, String email, String phone, String address) {
        User newUser = new User(name, email, phone, address);
        User savedUser = userRepository.save(newUser);
        return UserMapper.mapToUserDto(savedUser);
    }

    @Override
    public User getUserById(Long id, String authenticatedUsername) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("User Not found");
        }
        User user = optionalUser.get();
        // Ensure users can only access their own details
        if (!user.getName().equals(authenticatedUsername)) {
            throw new AccessDeniedException("You are not allowed to view this user’s data");
        }
        return user;
    }


    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::mapToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Long userId, String authenticatedUsername) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()){
            throw new IllegalArgumentException("User Not found!");
        }

        User user = optionalUser.get();
        if (!user.getName().equals(authenticatedUsername)) {
            throw new AccessDeniedException("You are not allowed to view this user’s data");
        }

        List<Account> allAccounts = accountRepository.findByUserId(userId);
        if (allAccounts.isEmpty()){
            userRepository.delete(user);
        } else {
            throw new IllegalArgumentException("First delete all account of the user");
        }
    }

    // ✅ User Update Their Own Information
    @Override
    public String updateUser(Long userId, String name, String email, String phone, String address, String authenticatedUsername) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            return "User not found!";
        }

        User user = optionalUser.get();
        if (!user.getName().equals(authenticatedUsername)) {
            throw new AccessDeniedException("You are not allowed to view this user’s data");
        }
        if (name != null && !name.isBlank()) user.setName(name);
        if (email != null && !email.isBlank()) user.setEmail(email);
        if (phone != null && !phone.isBlank()) user.setPhone(phone);
        if (address != null && !address.isBlank()) user.setAddress(address);

        userRepository.save(user);
        return "User details updated successfully!";
    }

    // Request Online Banking Activation
    @Override
    public String requestOnlineBanking(String eamil, String bankingPassword) {
        Optional<User> optionalUser = userRepository.findByEmail(eamil);
        if (optionalUser.isEmpty()) {
            return "User not found!";
        }

        User user = optionalUser.get();
        if (user.getOnlineBankingStatus() == OnlineBankingStatus.ACTIVE) {
            return "Online Banking is already activated!";
        }

        user.setBankingPassword(passwordEncoder.encode(bankingPassword));
        user.setOnlineBankingStatus(OnlineBankingStatus.PENDING_FOR_ACTIVATION);
        userRepository.save(user);

        return "Online banking activation request submitted. Awaiting admin approval.";
    }

    // Check Online Banking Status
    @Override
    public OnlineBankingStatus getOnlineBankingStatus(Long userId, String authenticatedUsername) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!user.getName().equals(authenticatedUsername)) {
            throw new AccessDeniedException("You are not allowed to view this user’s data");
        }

        return user.getOnlineBankingStatus();
    }
}
