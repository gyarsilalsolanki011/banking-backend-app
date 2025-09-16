package com.gyarsilalsolanki011.banking.controller;

import com.gyarsilalsolanki011.banking.models.dto.AccountDto;
import com.gyarsilalsolanki011.banking.models.dto.AdminDto;
import com.gyarsilalsolanki011.banking.models.dto.UserDto;
import com.gyarsilalsolanki011.banking.models.enums.AdminRole;
import com.gyarsilalsolanki011.banking.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;
    @Autowired
    private AccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<?> createAdmin(@RequestParam String username,
                                         @RequestParam String email,
                                         @RequestParam String password,
                                         @RequestParam String role) {
        AdminRole adminRole;
        try {
            adminRole = AdminRole.valueOf(role.toUpperCase());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid admin role! Choose: ADMIN, MANAGER, or SUPER_ADMIN.");
        }

        AdminDto newAdmin = adminService.createAdmin(username, email, password, adminRole);
        return ResponseEntity.ok(newAdmin);
    }

    // ✅ Admin Update User Endpoint
    @PutMapping("/update")
    public String updateAdmin(@RequestParam Long adminId,
                              @RequestParam(required = false) String username,
                              @RequestParam(required = false) String email,
                              @RequestParam(required = false) String password,
                              @RequestParam(required = false) AdminRole role) {
        return adminService.updateAdmin(adminId, username, email, password, role);
    }

    @GetMapping("/{adminId}")
    public ResponseEntity<?> getAdminById(@PathVariable Long adminId) {
        try {
            AdminDto admin = adminService.getAdminById(adminId);
            return ResponseEntity.ok(admin);
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{adminId}")
    public ResponseEntity<?> deleteAdmin(@PathVariable Long adminId) {
        try {
            adminService.deleteAdmin(adminId);
            return ResponseEntity.ok("Admin deleted successfully!");
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/approve-withdrawal")
    public ResponseEntity<?> approveWithdrawal(@RequestParam Long transactionId){
        try {
            adminService.approveWithdrawal(transactionId);
            return ResponseEntity.ok("Approved Successfully");
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Approve Online Banking Activation
    @PostMapping("/approve-online-banking")
    public String approveOnlineBanking(@RequestParam Long userId) {
        return adminService.approveOnlineBanking(userId);
    }

    // Deactivate Online Banking
    @PostMapping("/deactivate-online-banking")
    public String deactivateOnlineBanking(@RequestParam Long userId) {
        return adminService.deactivateOnlineBanking(userId);
    }

    @GetMapping("/all-admins")
    public ResponseEntity<List<AdminDto>> getAllAdmins() {
        List<AdminDto> allAdmins = adminService.getAllAdmins();
        return ResponseEntity.ok(allAdmins);
    }

    // Get all users
    @GetMapping("/all-users")
    public  ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> allUsers = userService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }

    // Get all accounts
    @GetMapping("/all-accounts")
    public ResponseEntity<List<AccountDto>> getAllAccounts(){
        List<AccountDto> allAccounts = accountService.getAllAccounts();
        return ResponseEntity.ok(allAccounts);
    }
}
