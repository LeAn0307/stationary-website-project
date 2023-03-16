
package com.shinhands.mu.Stationary.controller;

import com.shinhands.mu.Stationary.dto.SignUpDTO;
import com.shinhands.mu.Stationary.dto.UserDTO;
import com.shinhands.mu.Stationary.entity.UserRole;
import com.shinhands.mu.Stationary.security.CustomUserDetails;
import com.shinhands.mu.Stationary.security.JwtUtil;
import com.shinhands.mu.Stationary.dto.AccountDTO;

import com.shinhands.mu.Stationary.service.AccountService;
import com.shinhands.mu.Stationary.service.UserRoleService;
import com.shinhands.mu.Stationary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/accounts")
public class AccountController {
    @Autowired
    AccountService accountService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtTokenUtil;
    @Autowired
    private UserDetailsService userDetailsService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AccountDTO accountDTO) throws Exception {
        if (accountService.authentication(accountDTO)) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(accountDTO.getEmail());
            final String jwt = jwtTokenUtil.generateToken((CustomUserDetails) userDetails);
            return ResponseEntity.ok().body(jwt);
        }
        return ResponseEntity.badRequest().build();
    }

//    @RequestMapping(value="",method= RequestMethod.GET)
//    public ResponseEntity getAllAccounts()
//    {
//        return ResponseEntity.ok().body(accountService.getAllAccounts());
//    }
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpDTO signUpDTO)
    {
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
                        return ResponseEntity.badRequest().build();
                    } else{
                        UserDetails userDetails = userDetailsService.loadUserByUsername(accountDTO.getEmail());
                        String jwt = jwtTokenUtil.generateToken((CustomUserDetails) userDetails);
                        return ResponseEntity.ok().body(jwt);
                    }

                } else {
                    accountService.deleteAccountByEmail(accountDTO.getEmail());
                    return ResponseEntity.badRequest().build();
                }
            }
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
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
