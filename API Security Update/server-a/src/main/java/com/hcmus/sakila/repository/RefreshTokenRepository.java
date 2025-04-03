package com.hcmus.sakila.repository;

import com.hcmus.sakila.model.Account;
import com.hcmus.sakila.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {

    RefreshToken findByUser (Account account);

    RefreshToken findByToken(String token);
}