package com.gyarsilalsolanki011.banking.repository;

import com.gyarsilalsolanki011.banking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
    User findByName(String name);
}
