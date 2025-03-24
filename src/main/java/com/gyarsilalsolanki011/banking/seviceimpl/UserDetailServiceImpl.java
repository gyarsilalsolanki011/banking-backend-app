package com.gyarsilalsolanki011.banking.seviceimpl;

import com.gyarsilalsolanki011.banking.entity.Admin;
import com.gyarsilalsolanki011.banking.entity.User;
import com.gyarsilalsolanki011.banking.repository.AdminRepository;
import com.gyarsilalsolanki011.banking.repository.UserRepository;
import com.gyarsilalsolanki011.banking.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailService {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Admin> optionalAdmin = adminRepository.findByUsername(username);
        Optional<User> optionalUser = userRepository.findByName(username);
        if (optionalAdmin.isPresent()){
            Admin admin = optionalAdmin.get();
            return org.springframework.security.core.userdetails.User.builder()
                    .username(admin.getUsername())
                    .password(admin.getPassword())
                    .roles(admin.getRole().toString())
                    .build();
        } else if (optionalUser.isPresent()){
            User user = optionalUser.get();
            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getName())
                    .password(user.getBankingPassword())
                    .roles("USER")
                    .build();
        }
        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
