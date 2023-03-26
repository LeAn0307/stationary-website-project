//package com.shinhands.mu.Stationary.config.security;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
//
//@Configuration
//public class HashPasswordConfig {
//    private String _secret = "mu-shinhands-stationary";
//    private int _iteration = 1000;
//    private int _hashWidth = 128;
//    private Pbkdf2PasswordEncoder _encoder = new Pbkdf2PasswordEncoder(_secret, _iteration, _hashWidth);
//
//    public boolean isMatch(String rawPassword, String encodePassword) {
//        return _encoder.matches(rawPassword, encodePassword);
//    }
//
//    public String encode(String password) {
//        return _encoder.encode(password);
//    }
//}
