package com.hcmus.sakila.repository;

import com.hcmus.sakila.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    Optional<Account> findByUsername(String username);

    Boolean existsByUsername(String username);
}