package com.example.demo.user.service;

import com.example.demo.user.domain.User;
import com.example.demo.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Objects;

@Slf4j
@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);

        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }

        final String authenticatedUsername = user.getUsername();
        final String authenticatedPassword = user.getHashPassword();

        return new org.springframework.security.core.userdetails.User(
                authenticatedUsername,
                authenticatedPassword,
                Collections.singletonList(new SimpleGrantedAuthority("USER")));
    }
}
