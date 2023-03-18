package com.shinhands.mu.Stationary.service;

import com.shinhands.mu.Stationary.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserWebService {
    public List<UserDTO> getAllUsers();

    public Boolean addUser(UserDTO userDTO);

    public Boolean deleteUser(Long id);

    public UserDTO getUserById(long id);

    public Boolean updateUser(long id, UserDTO userDTO);

    public Boolean checkAdmin(String email, String password);

    public UserDTO getUserByAccount(String email, String password);
}
