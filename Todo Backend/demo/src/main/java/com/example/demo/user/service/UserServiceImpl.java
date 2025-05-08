package com.example.demo.user.service;

import com.example.demo.user.domain.User;
import com.example.demo.user.repository.UserRepository;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User findByUsername(String username) {

       return userRepository.findByUsername(username);

    }

    @Override
    public User findByUserId(UUID userId) {

        return userRepository.findByUserId(userId);
    }
}
