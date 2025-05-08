package com.example.demo.user.repository;

import com.example.demo.user.domain.RefreshToken;
import com.example.demo.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {

    RefreshToken findByUser (User user);

    RefreshToken findByToken(String token);
}