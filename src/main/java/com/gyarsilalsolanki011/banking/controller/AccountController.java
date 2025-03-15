package com.gyarsilalsolanki011.banking.controller;

import com.gyarsilalsolanki011.banking.dto.AccountDto;
import com.gyarsilalsolanki011.banking.entity.User;
import com.gyarsilalsolanki011.banking.enums.AccountType;
import com.gyarsilalsolanki011.banking.service.AccountService;
import com.gyarsilalsolanki011.banking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;

    // Create Account API
    @PostMapping("/create")
    public ResponseEntity<?> createAccount(@RequestParam Long userId,
                                           @RequestParam String accountType,
                                           @RequestParam double balance){
        User user;
        try {
            user = userService.getUserById(userId);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        AccountType type;
        try {
            type = AccountType.valueOf(accountType.toUpperCase());
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body("Invalid account type! Choose: SAVINGS, CURRENT, or FIXED_DEPOSIT.");
        }

        AccountDto newAccount = accountService.createAccount(user, type, balance);
        return ResponseEntity.ok(newAccount);
    }

    // Deposit API
    @PostMapping("/{accountId}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long accountId,
                                              @RequestParam double amount) {
        AccountDto updatedAccount = accountService.deposit(accountId, amount);
        return ResponseEntity.ok(updatedAccount);
    }

    // Withdraw API
    @PostMapping("/{accountId}/withdraw")
    public ResponseEntity<?> withdraw(@PathVariable Long accountId,
                                      @RequestParam double amount) {
        try {
            AccountDto updatedAccount = accountService.withdraw(accountId, amount);
            return ResponseEntity.ok(updatedAccount);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Delete API
    @DeleteMapping("/{accountId}/delete")
    public  ResponseEntity<?> delete(@PathVariable Long accountId){
        try {
            accountService.delete(accountId);
            return ResponseEntity.ok("Account deleted successfully");
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}