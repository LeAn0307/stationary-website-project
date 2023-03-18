package com.shinhands.mu.Stationary.controller;

import com.shinhands.mu.Stationary.dto.AccountDTO;
import com.shinhands.mu.Stationary.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/{email}")
    public ResponseEntity<?> findAccountByEmail(@PathVariable String email) {
        try {
            AccountDTO accountDTO = accountService.getAccountByEmail(email);
            if(accountDTO == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(accountDTO, HttpStatus.OK);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
