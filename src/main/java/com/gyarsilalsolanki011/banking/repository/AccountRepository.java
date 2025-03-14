package com.gyarsilalsolanki011.banking.repository;

import com.gyarsilalsolanki011.banking.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByUserId(Long userId);
}
