package com.gyarsilalsolanki011.banking.controller;

import com.gyarsilalsolanki011.banking.models.dto.AccountDto;
import com.gyarsilalsolanki011.banking.models.dto.UserDto;
import com.gyarsilalsolanki011.banking.models.entity.User;
import com.gyarsilalsolanki011.banking.mapper.UserMapper;
import com.gyarsilalsolanki011.banking.models.dto.StringResponse;
import com.gyarsilalsolanki011.banking.repository.UserRepository;
import com.gyarsilalsolanki011.banking.service.AccountService;
import com.gyarsilalsolanki011.banking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/get-one")
    public ResponseEntity<UserDto> getUserById(@RequestParam String email, Principal principal){
        try {
            String username = principal.getName();

            User userForId = userRepository.findByEmail(email).orElseThrow();
            Long userId = userForId.getId();

            UserDto user = UserMapper.mapToUserDto(userService.getUserById(userId, username));
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
           throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<StringResponse> deleteUser(@RequestParam String email, Principal principal){
        try {
            String name = principal.getName();
            userService.deleteUser(email, name);
            return ResponseEntity.ok(new StringResponse("User Deleted successfully"));
        } catch (IllegalArgumentException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/all-accounts")
    public ResponseEntity<List<AccountDto>> getAllAccountsByUserId(@RequestParam String email, Principal principal){
        try {
            String name = principal.getName();
            User userForId = userRepository.findByEmail(email).orElseThrow();
            Long userId = userForId.getId();

            if (!name.equals(userForId.getName())) {
                throw new AccessDeniedException("U are not allowed to view this detail");
            }

            List<AccountDto> accounts = accountService.getAllAccountsByUserId(userId);
            return ResponseEntity.ok(accounts);
        } catch (IllegalArgumentException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    // ✅ User Update Endpoint
    @PutMapping("/update")
    public ResponseEntity<StringResponse> updateUser(@RequestParam String originalEmail,
                                                     @RequestParam(required = false) String name,
                                                     @RequestParam(required = false) String email,
                                                     @RequestParam(required = false) String phone,
                                                     @RequestParam(required = false) String address,
                                                     Principal principal) {
        String authenticateName = principal.getName();
        String response = userService.updateUser(originalEmail, name, email, phone, address, authenticateName);
        return ResponseEntity.ok(new StringResponse(response));
    }

    // Check Online Banking Status
    @GetMapping("/online-banking-status")
    public ResponseEntity<StringResponse> getOnlineBankingStatus(@RequestParam String email, Principal principal) {
        String name = principal.getName();
        return ResponseEntity.ok(new StringResponse(userService.getOnlineBankingStatus(email, name)));
    }
}