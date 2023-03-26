package com.shinhands.mu.Stationary.controller;

import com.shinhands.mu.Stationary.dto.AccountDTO;
import com.shinhands.mu.Stationary.dto.ProductDTO;
import com.shinhands.mu.Stationary.dto.UserDTO;
import com.shinhands.mu.Stationary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("")
@RequestMapping(value="/users")
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping(value="",method= RequestMethod.GET)
    public ResponseEntity getAllUsserProducts()
    {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }
    @PostMapping()
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDTO)
    {
        return ResponseEntity.ok().body(userService.addUser(userDTO));
    }
    @GetMapping(value="/{id}")
    public ResponseEntity getUserById(@PathVariable(name="id") long id)
    {
        return ResponseEntity.ok().body(userService.getUserById(id));
    }
    @PutMapping(value="/{id}")
    public ResponseEntity updateUser(@PathVariable(name="id") long id,@RequestBody UserDTO userDTO)
    {
        return ResponseEntity.ok().body(userService.updateUser(id,userDTO));
    }
    @DeleteMapping(value="/{id}")
    public ResponseEntity deleteUser(@PathVariable(name="id") long id)
    {
        return ResponseEntity.ok().body(userService.deleteUser(id));
    }

    @PostMapping(value = "/login")
    public ResponseEntity<UserDTO> loginUser(@RequestBody AccountDTO accountDTO) {
        try {
            UserDTO userDTO = userService.getUserByAccount(accountDTO.getEmail(), accountDTO.getAccountPassword());
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
