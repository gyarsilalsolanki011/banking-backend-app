package com.gyarsilalsolanki011.banking.controller;

import com.gyarsilalsolanki011.banking.entity.Admin;
import com.gyarsilalsolanki011.banking.enums.AdminRole;
import com.gyarsilalsolanki011.banking.models.AdminLoginResponse;
import com.gyarsilalsolanki011.banking.repository.AdminRepository;
import com.gyarsilalsolanki011.banking.utills.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AdminRepository adminRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String email, @RequestParam String password, @RequestParam String role) {
        if (adminRepository.findByUsername(username).isPresent()) {
            return "Admin already exists";
        }

        AdminRole adminRole;
        try {
            adminRole = AdminRole.valueOf(role.toUpperCase());
        } catch (IllegalArgumentException e) {
            return "Invalid admin role! Choose: ADMIN, MANAGER, or SUPER_ADMIN.";
        }

        Admin admin = new Admin(username, email, passwordEncoder.encode(password), adminRole);
        adminRepository.save(admin);

        return "User registered successfully";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
        Optional<Admin> optionalAdmin = adminRepository.findByEmail(email);
        if (optionalAdmin.isEmpty()){
            return ResponseEntity.badRequest().body("Admin Not found");
        }

        Admin admin = optionalAdmin.get();
        if (!passwordEncoder.matches(password, admin.getPassword())) {
            return ResponseEntity.badRequest().body("Invalid credentials");
        }
        String token = jwtUtil.generateToken(admin.getUsername(), admin.getRole().toString());
        return ResponseEntity.ok(new AdminLoginResponse(token, admin.getRole().toString()));
    }
}
