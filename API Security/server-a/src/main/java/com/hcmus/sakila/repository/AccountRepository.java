package com.hcmus.sakila.repository;

import com.hcmus.sakila.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findByUsernameAndPassword(String username, String password);

    Account findByRefreshToken(String refreshToken);
}