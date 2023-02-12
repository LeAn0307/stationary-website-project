package com.shinhands.mu.Stationary.controller;

import com.shinhands.mu.Stationary.config.security.JwtUtil;
import com.shinhands.mu.Stationary.dto.AccountDTO;
import com.shinhands.mu.Stationary.dto.UserDTO;
import com.shinhands.mu.Stationary.form.RegisterForm;
import com.shinhands.mu.Stationary.service.AccountService;
import com.shinhands.mu.Stationary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController("")
@RequestMapping(value="/accounts")
public class AccountController {
    @Autowired
    AccountService accountService;
    @Autowired
    private UserService _userService;
    @Autowired
    private JwtUtil jwtTokenUtil;
    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterForm body) {
        AccountDTO accountDTO = new AccountDTO();
        if (accountService.getAccountByEmail(body.getEmail()) == null) {
            return ResponseEntity.badRequest().build();
        }
        accountDTO.setAccountPassword(body.getAccountPassword());
        accountDTO.setEmail(body.getEmail());
        accountDTO = accountService.addAccount(accountDTO);
        UserDTO userDTO = new UserDTO();
        userDTO.setUserName(body.getUserName());
        userDTO.setPhone(body.getPhone());
        userDTO.setAddress(body.getAddress());
        userDTO.setIdAccount(accountDTO.getId());
        _userService.addUser(userDTO);
        if (accountService.authentication(accountDTO)) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(accountDTO.getEmail());
            String jwt = jwtTokenUtil.generateToken(userDetails);
            return ResponseEntity.ok().body(jwt);
        }
        return ResponseEntity.badRequest().build();
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AccountDTO accountDTO) throws Exception {
        if (accountService.authentication(accountDTO)) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(accountDTO.getEmail());
            String jwt = jwtTokenUtil.generateToken(userDetails);
            return ResponseEntity.ok().body(jwt);
        }
        return ResponseEntity.badRequest().build();
    }
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
