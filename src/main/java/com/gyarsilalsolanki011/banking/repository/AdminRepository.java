package com.gyarsilalsolanki011.banking.repository;

import com.gyarsilalsolanki011.banking.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByUsername(String username);
    Admin findByEmail(String email);
}
