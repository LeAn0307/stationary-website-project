package com.shinhands.mu.Stationary.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private Long id;

    @Email(message = "Email phải hợp lệ")
    private String email;

    @NotBlank(message = "Mật khẩu không được để trống")
    private String accountPassword;
    private Long userId;
    private String fullName;
    private List<String> roleList;
}
