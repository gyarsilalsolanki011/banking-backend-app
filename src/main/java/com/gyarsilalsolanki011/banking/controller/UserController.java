package com.gyarsilalsolanki011.banking.controller;

import com.gyarsilalsolanki011.banking.dto.UserDto;
import com.gyarsilalsolanki011.banking.entity.User;
import com.gyarsilalsolanki011.banking.mapper.UserMapper;
import com.gyarsilalsolanki011.banking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestParam String name,
                                              @RequestParam String email,
                                              @RequestParam String phone,
                                              @RequestParam String address){
        UserDto newUser = userService.createUser(name, email, phone, address);
        return ResponseEntity.ok(newUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id){
        Optional<UserDto> user = userService.getUserById(id)
                .map(UserMapper::mapToUserDto);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().body(null));
    }

    @DeleteMapping("/{userId}/delete")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId){
        try {
            userService.deleteUser(userId);
            return ResponseEntity.ok("User Deleted successfully");
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}