package com.example.demo.user.service;



import com.example.demo.user.domain.User;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public interface UserService {
    User findByUsername(String username);

    User findByUserId(UUID userId);
}
