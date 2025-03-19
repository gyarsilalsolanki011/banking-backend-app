package com.gyarsilalsolanki011.banking.controller;

import com.gyarsilalsolanki011.banking.dto.AccountDto;
import com.gyarsilalsolanki011.banking.dto.UserDto;
import com.gyarsilalsolanki011.banking.enums.OnlineBankingStatus;
import com.gyarsilalsolanki011.banking.mapper.UserMapper;
import com.gyarsilalsolanki011.banking.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestParam String name,
                                              @RequestParam String email,
                                              @RequestParam String phone,
                                              @RequestParam String address){
        UserDto newUser = userService.createUser(name, email, phone, address);
        return ResponseEntity.ok(newUser);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId){
        try {
            UserDto user = UserMapper.mapToUserDto(userService.getUserById(userId));
            return ResponseEntity.ok(user);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId){
        try {
            userService.deleteUser(userId);
            return ResponseEntity.ok("User Deleted successfully");
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all-accounts")
    public ResponseEntity<?> getAllAccountsByUserId(@RequestParam Long userId){
        try {
            List<AccountDto> accounts = accountService.getAllAccountsByUserId(userId);
            return ResponseEntity.ok(accounts);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // ✅ User Update Endpoint
    @PutMapping("/update")
    public String updateUser(@RequestParam Long userId,
                             @RequestParam(required = false) String name,
                             @RequestParam(required = false) String email,
                             @RequestParam(required = false) String phone,
                             @RequestParam(required = false) String address) {
        return userService.updateUser(userId, name, email, phone, address);
    }


    // Request Online Banking Activation
    @PostMapping("/request-online-banking")
    public String requestOnlineBanking(@RequestParam Long userId, @RequestParam String bankingPassword) {
        return userService.requestOnlineBanking(userId, bankingPassword);
    }

    // Check Online Banking Status
    @GetMapping("/online-banking-status")
    public OnlineBankingStatus getOnlineBankingStatus(@RequestParam Long userId) {
        return userService.getOnlineBankingStatus(userId);
    }
}