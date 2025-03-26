package com.gyarsilalsolanki011.banking.controller;

import com.gyarsilalsolanki011.banking.dto.UserDto;
import com.gyarsilalsolanki011.banking.entity.Admin;
import com.gyarsilalsolanki011.banking.entity.User;
import com.gyarsilalsolanki011.banking.enums.AdminRole;
import com.gyarsilalsolanki011.banking.models.LoginResponse;
import com.gyarsilalsolanki011.banking.models.StringResponse;
import com.gyarsilalsolanki011.banking.repository.AdminRepository;
import com.gyarsilalsolanki011.banking.repository.UserRepository;
import com.gyarsilalsolanki011.banking.service.UserService;
import com.gyarsilalsolanki011.banking.utills.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UserService userService;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/register")
    public String registerAdmin(@RequestParam String username, @RequestParam String email, @RequestParam String password, @RequestParam String role) {
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

        // Check if User or Admin
        Optional<User> userOptional = userRepository.findByEmail(email);
        Optional<Admin> adminOptional = adminRepository.findByEmail(email);

        String username = null;
        if (adminOptional.isPresent()){
            Admin admin = adminOptional.get();
            username = admin.getUsername();
            if (!passwordEncoder.matches(password, admin.getPassword())) {
                return ResponseEntity.badRequest().body("Invalid credentials");
            }
        } else if (userOptional.isPresent()) {
            User user = userOptional.get();
            username = user.getName();
            if (!passwordEncoder.matches(password, user.getBankingPassword())) {
                return ResponseEntity.badRequest().body("Invalid credentials");
            }
        } else {
            return ResponseEntity.badRequest().body("User or Admin Not found");
        }

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        String token = jwtUtil.generateToken(authentication);

        return ResponseEntity.ok(new LoginResponse(token, authentication.getAuthorities().iterator().next().getAuthority()));
    }

    @PostMapping("/create")
    public ResponseEntity<StringResponse> createUser(@RequestParam String name,
                                              @RequestParam String email,
                                              @RequestParam String phone,
                                              @RequestParam String address){
        UserDto newUser = userService.createUser(name, email, phone, address);
        return ResponseEntity.ok(new StringResponse("User Register successfully"));
    }

    // Request Online Banking Activation
    @PostMapping("/request-online-banking")
    public ResponseEntity<StringResponse> requestOnlineBanking(@RequestParam String email,
                                       @RequestParam String bankingPassword) {
        return ResponseEntity.ok(new StringResponse(userService.requestOnlineBanking(email, bankingPassword)));
    }
}
