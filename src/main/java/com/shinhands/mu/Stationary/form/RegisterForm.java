package com.shinhands.mu.Stationary.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterForm {
    private String email;
    private String accountPassword;
    private String userName;
    private String address;
    private String phone;
}
