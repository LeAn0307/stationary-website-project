package com.shinhands.mu.Stationary.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDTO {

    @NotBlank(message = "Tên người dùng không được để trống")
    private String userName;

    @NotBlank(message = "Địa chỉ không được để trống")
    private String address;

    @NotBlank(message = "Số điện thoại không được để trống")
    private String phone;


    @Email(message = "Email phải hợp lệ")
    private String email;

    @NotBlank(message = "Mật khẩu không được để trống")
    private String accountPassword;

}
