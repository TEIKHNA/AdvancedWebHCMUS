package com.example.demo.user.repository;

import com.example.demo.user.domain.RefreshToken;
import com.example.demo.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {

    RefreshToken findByUser_UserId(UUID userId);

    RefreshToken findByToken(String token);
}