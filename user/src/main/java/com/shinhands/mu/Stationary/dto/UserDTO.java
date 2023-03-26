package com.shinhands.mu.Stationary.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;

    @NotBlank(message = "Username cannot be blank")
    private String userName;
    @NotBlank(message = "Address cannot be blank")
    private String address;

    @NotBlank(message = "Phone cannot be blank")
    private String phone;
    private Long idAccount;


    @Email(message = "Email should be valid")
    private String email;
}
