package com.gyarsilalsolanki011.banking.seviceimpl;

import com.gyarsilalsolanki011.banking.entity.Admin;
import com.gyarsilalsolanki011.banking.repository.AdminRepository;
import com.gyarsilalsolanki011.banking.repository.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailService {
    @Autowired
    private AdminRepository adminRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByUsername(username);
        if (admin != null){
            return org.springframework.security.core.userdetails.User.builder()
                    .username(admin.getUsername())
                    .password(admin.getPassword())
                    .roles(admin.getRole().toString())
                    .build();
        }
        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
