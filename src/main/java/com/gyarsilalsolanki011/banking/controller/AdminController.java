package com.gyarsilalsolanki011.banking.controller;

import com.gyarsilalsolanki011.banking.dto.AccountDto;
import com.gyarsilalsolanki011.banking.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AccountService accountService;
    public AdminController(AccountService accountService) {
        this.accountService = accountService;
    }

    //get all accounts REST API
    @GetMapping("/all-accounts")
    public ResponseEntity<List<AccountDto>> getAllAccounts(){
        List<AccountDto> allAccounts = accountService.getAllAccounts();
        return new ResponseEntity<>(allAccounts, HttpStatus.OK);
    }
}
