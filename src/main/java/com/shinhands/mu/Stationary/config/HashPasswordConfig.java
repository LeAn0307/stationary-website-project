package com.shinhands.mu.Stationary.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

@Configuration
public class HashPasswordConfig {
    @Value("${password.secret.key}")
    private String _secret;
    private int _iteration = 1000;
    private int _hashWidth = 128;

    @Bean
    public Pbkdf2PasswordEncoder _encoder() {
        return new Pbkdf2PasswordEncoder(_secret, _iteration, _hashWidth);
    }

    public boolean isMatch(String rawPassword, String encodePassword) {
        return _encoder().matches(rawPassword, encodePassword);
    }

    public String encode(String password) {
        return _encoder().encode(password);
    }
}
