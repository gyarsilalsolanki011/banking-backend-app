package com.gyarsilalsolanki011.banking.controller;

import com.gyarsilalsolanki011.banking.dto.AccountDto;
import com.gyarsilalsolanki011.banking.entity.User;
import com.gyarsilalsolanki011.banking.enums.AccountType;
import com.gyarsilalsolanki011.banking.models.StringResponse;
import com.gyarsilalsolanki011.banking.repository.UserRepository;
import com.gyarsilalsolanki011.banking.service.AccountService;
import com.gyarsilalsolanki011.banking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    // Create Account API
    @PostMapping("/create")
    public ResponseEntity<StringResponse> createAccount(@RequestParam String email,
                                                        @RequestParam String accountType,
                                                        @RequestParam double balance,
                                                        Principal principal){
        User user;
        try {
            String username = principal.getName();

            User userForId = userRepository.findByEmail(email).orElseThrow();
            Long userId = userForId.getId();

            user = userService.getUserById(userId, username);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(new StringResponse(e.getMessage()));
        }

        AccountType type;
        try {
            type = AccountType.valueOf(accountType.toUpperCase());
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(new StringResponse("Invalid account type! Choose: SAVINGS, CURRENT, or FIXED_DEPOSIT."));
        }

        AccountDto newAccount = accountService.createAccount(user, type, balance);
        return ResponseEntity.ok(new StringResponse("Account created successfully"));
    }

    @GetMapping("/get-one")
    public ResponseEntity<AccountDto> getAccountById(@RequestParam String accountType,
                                                     @RequestParam String email,
                                                     Principal principal){
        try {
            AccountType type;
            try {
                type = AccountType.valueOf(accountType.toUpperCase());
            } catch (IllegalArgumentException e){
                throw new IllegalArgumentException("Invalid account type! Choose: SAVINGS, CURRENT, or FIXED_DEPOSIT.");
            }

            AccountDto account = null;
            String name = principal.getName();

            User userForId = userRepository.findByEmail(email).orElseThrow();
            Long userId = userForId.getId();

            userService.getUserById(userId, name);
            List<AccountDto> allAccounts = accountService.getAllAccountsByUserId(userId);
            for (AccountDto accountDto : allAccounts){
                if (accountDto.getAccountType() == type){
                    account = accountService.getAccountById(accountDto.getAccountId());
                }
            }
            assert account != null;
            return ResponseEntity.ok(account);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    // Delete API
    @DeleteMapping("/delete")
    public  ResponseEntity<StringResponse> delete(@RequestParam String accountType,
                                                  @RequestParam String email,
                                                  Principal principal){
        try {
            AccountType type;
            try {
                type = AccountType.valueOf(accountType.toUpperCase());
            } catch (IllegalArgumentException e){
                return ResponseEntity.badRequest().body(new StringResponse("Invalid account type! Choose: SAVINGS, CURRENT, or FIXED_DEPOSIT."));
            }

            String name = principal.getName();

            User userForId = userRepository.findByEmail(email).orElseThrow();
            Long userId = userForId.getId();

            userService.getUserById(userId, name);
            List<AccountDto> allAccounts = accountService.getAllAccountsByUserId(userId);
            for (AccountDto accountDto : allAccounts){
                if (accountDto.getAccountType() == type){
                    accountService.delete(accountDto.getAccountId());
                }
            }

            return ResponseEntity.ok(new StringResponse("Account deleted successfully"));
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(new StringResponse(e.getMessage()));
        }
    }

}