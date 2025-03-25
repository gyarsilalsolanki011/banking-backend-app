package com.gyarsilalsolanki011.banking.controller;

import com.gyarsilalsolanki011.banking.dto.TransactionDto;
import com.gyarsilalsolanki011.banking.service.TransactionService;
import com.gyarsilalsolanki011.banking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private UserService userService;

    @PostMapping("/deposit/{accountId}")
    public ResponseEntity<?> deposit(@PathVariable Long accountId, @RequestParam double amount, @RequestParam Long userId, Principal principal) {
        try {
            String name = principal.getName();
            userService.getUserById(userId, name);
            TransactionDto transaction = transactionService.deposit(accountId, amount);
            return ResponseEntity.ok(transaction);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/withdraw/{accountId}")
    public ResponseEntity<?> withdraw(@PathVariable Long accountId, @RequestParam double amount, @RequestParam Long userId, Principal principal) {
        try {
            String name = principal.getName();
            userService.getUserById(userId, name);
            TransactionDto transaction = transactionService.withdraw(accountId, amount);
            return ResponseEntity.ok(transaction);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{fromAccountId}/transfer/{toAccountId}")
    public ResponseEntity<?> transfer(@PathVariable Long fromAccountId, @PathVariable Long toAccountId, double amount, @RequestParam Long userId, Principal principal){
        try {
            String name = principal.getName();
            userService.getUserById(userId, name);
            TransactionDto transaction = transactionService.transfer(fromAccountId, toAccountId, amount);
            return ResponseEntity.ok(transaction);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<?> getTransactions(@PathVariable Long accountId, @RequestParam Long userId, Principal principal) {
        try {
            String name = principal.getName();
            userService.getUserById(userId, name);
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body("Bad Request");
        }
        return ResponseEntity.ok(transactionService.getTransactionsByAccountId(accountId));
    }
}
