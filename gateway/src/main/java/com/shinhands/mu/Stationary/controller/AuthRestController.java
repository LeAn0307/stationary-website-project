package com.shinhands.mu.Stationary.controller;

import com.shinhands.mu.Stationary.dto.AccountDTO;
import com.shinhands.mu.Stationary.dto.AuthDTO;
import com.shinhands.mu.Stationary.dto.SignUpDTO;
import com.shinhands.mu.Stationary.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/auth")
public class AuthRestController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RestTemplate restTemplate;
    private String urlPage = "localhost:8765";

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthDTO authDTO) {
        Boolean isAuthen = restTemplate.postForObject("http://"+urlPage+"/api/accounts/authen", authDTO, Boolean.class);
        if(isAuthen) {
            AccountDTO accountDTO = restTemplate.getForObject("http://"+urlPage+"/api/accounts/" + authDTO.getEmail(), AccountDTO.class);
            String token = jwtUtil.generateToken(accountDTO);
            return new ResponseEntity<>(token, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody SignUpDTO signUpDTO) {
        Boolean isSuscessRegister = restTemplate.postForObject("http://"+urlPage+"/api/accounts", signUpDTO, Boolean.class);
        if(isSuscessRegister) {
            AccountDTO accountDTO = restTemplate.getForObject("http://"+urlPage+"/api/accounts/" + signUpDTO.getEmail(), AccountDTO.class);
            String token = jwtUtil.generateToken(accountDTO);
            return new ResponseEntity<String>(token, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<String>("", HttpStatus.BAD_REQUEST);
        }
    }
}