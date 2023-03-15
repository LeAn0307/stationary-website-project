package com.shinhands.mu.Stationary.config;

import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsConfig implements ReactiveUserDetailsService {

    private final InMemoryUserDetailsManager inMemoryUserDetailsManager;

    public UserDetailsConfig() {
        List<UserDetails> users = new ArrayList<>();
        users.add(User.withDefaultPasswordEncoder()
                .username("123")
                .password("123")
                .roles("USER")
                .build());
        this.inMemoryUserDetailsManager = new InMemoryUserDetailsManager(users);
    }

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return Mono.fromCallable(() -> inMemoryUserDetailsManager.loadUserByUsername(username));
    }
}