package com.shinhands.mu.Stationary.controller;

import com.shinhands.mu.Stationary.dto.AccountDTO;
import com.shinhands.mu.Stationary.dto.UserDTO;
import com.shinhands.mu.Stationary.service.UserWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController("")
@RequestMapping(value="/api/users")
public class UserController {
    @Autowired
    private UserWebService userWebService;

    @RequestMapping(value="",method= RequestMethod.GET)
    public ResponseEntity getAllUsserProducts()
    {
        return ResponseEntity.ok().body(userWebService.getAllUsers());
    }

    @GetMapping(value="/{id}")
    public ResponseEntity getUserById(@PathVariable(name="id") long id)
    {
        return ResponseEntity.ok().body(userWebService.getUserById(id));
    }
    @PutMapping(value="/{id}")
    public ResponseEntity updateUser(@PathVariable(name="id") long id,@RequestBody UserDTO userDTO)
    {
        return ResponseEntity.ok().body(userWebService.updateUser(id,userDTO));
    }
    @DeleteMapping(value="/{id}")
    public ResponseEntity deleteUser(@PathVariable(name="id") long id)
    {
        return ResponseEntity.ok().body(userWebService.deleteUser(id));
    }

    @PostMapping(value = "/login")
    public ResponseEntity<UserDTO> loginUser(@RequestBody @Valid AccountDTO accountDTO) {
        try {
            UserDTO userDTO = userWebService.getUserByAccount(accountDTO.getEmail(), accountDTO.getAccountPassword());
            if(userDTO != null) {
                return ResponseEntity.ok().body(userDTO);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
