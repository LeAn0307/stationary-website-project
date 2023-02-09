package com.shinhands.mu.Stationary.controller;

import com.shinhands.mu.Stationary.dto.AccountDTO;
import com.shinhands.mu.Stationary.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("")
@RequestMapping(value="/accounts")
public class AccountController {
    @Autowired
    AccountService accountService;

    @RequestMapping(value="",method= RequestMethod.GET)
    public ResponseEntity getAllAccounts()
    {
        return ResponseEntity.ok().body(accountService.getAllAccounts());
    }
    @PostMapping()
    public ResponseEntity<AccountDTO> addUser(@RequestBody AccountDTO accountDTO)
    {
        return ResponseEntity.ok().body(accountService.addAccount(accountDTO));
    }
    @GetMapping(value="/{id}")
    public ResponseEntity getAccountById(@PathVariable(name="id") long id)
    {
        return ResponseEntity.ok().body(accountService.getAccountById(id));
    }
    @PutMapping(value="/{id}")
    public ResponseEntity updateAccount(@PathVariable(name="id") long id,@RequestBody AccountDTO accountDTO)
    {
        return ResponseEntity.ok().body(accountService.updateAccount(id,accountDTO));
    }
    @DeleteMapping(value="/{id}")
    public ResponseEntity deleteAccount(@PathVariable(name="id") long id)
    {
        return ResponseEntity.ok().body(accountService.deleteAccount(id));
    }

}
