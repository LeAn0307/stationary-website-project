package com.shinhands.mu.Stationary.config;

public interface SecurityConfig {
    boolean isMatch(String rawPassword, String encodePassword);
    String encode(String password);
}
