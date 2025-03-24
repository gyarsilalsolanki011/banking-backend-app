package com.gyarsilalsolanki011.banking.repository;

import com.gyarsilalsolanki011.banking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByEmail(String email);
    Optional<User> findByName(String name);
}
