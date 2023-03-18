package com.shinhands.mu.Stationary.controller;

import com.shinhands.mu.Stationary.config.HashPasswordConfig;
import com.shinhands.mu.Stationary.dto.AccountDTO;
import com.shinhands.mu.Stationary.dto.AuthDTO;
import com.shinhands.mu.Stationary.dto.SignUpDTO;
import com.shinhands.mu.Stationary.dto.UserDTO;
import com.shinhands.mu.Stationary.entity.UserRole;
import com.shinhands.mu.Stationary.service.AccountService;
import com.shinhands.mu.Stationary.service.UserRoleService;
import com.shinhands.mu.Stationary.service.UserWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private HashPasswordConfig hashPasswordConfig;

    @Autowired
    private UserWebService userService;

    @Autowired
    private UserRoleService userRoleService;

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

    @PostMapping ("/authen")
    public ResponseEntity<?> authenAccount(@RequestBody AuthDTO authDTO) {
        AccountDTO accountDTO = accountService.getAccountByEmail(authDTO.getEmail());
        if(accountDTO != null) {
            if(hashPasswordConfig.isMatch(authDTO.getPassword(), accountDTO.getAccountPassword())) {
                return new ResponseEntity<>(true, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> registerAccount(@RequestBody SignUpDTO signUpDTO) {
        try {
            if(accountService.isExistAccount(signUpDTO.getEmail())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Account already exists");
            } else {
                //Create New Account
                AccountDTO accountDTO = new AccountDTO();
                accountDTO.setEmail(signUpDTO.getEmail());
                accountDTO.setAccountPassword(signUpDTO.getAccountPassword());
                AccountDTO newAccountDTO = accountService.addAccount(accountDTO);

                //Create New User
                UserDTO userDTO = new UserDTO();
                userDTO.setUserName(signUpDTO.getUserName());
                userDTO.setPhone(signUpDTO.getPhone());
                userDTO.setAddress(signUpDTO.getAddress());
                userDTO.setIdAccount(newAccountDTO.getId());

                if(userService.addUser(userDTO)) {
                    UserRole newUserRole = new UserRole(null, 2L, newAccountDTO.getId(), 0L);
                    if(userRoleService.addUserRole(newUserRole) == false) {
                        accountService.deleteAccountByEmail(accountDTO.getEmail());
                        return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
                    } else {
                        return new ResponseEntity<>(true, HttpStatus.CREATED);
                    }
                } else {
                    accountService.deleteAccountByEmail(accountDTO.getEmail());
                    return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
                }
            }
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
