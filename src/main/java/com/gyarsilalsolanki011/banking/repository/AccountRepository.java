package com.gyarsilalsolanki011.banking.repository;

import com.gyarsilalsolanki011.banking.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
