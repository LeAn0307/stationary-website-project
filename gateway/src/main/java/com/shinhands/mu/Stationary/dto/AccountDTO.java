package com.shinhands.mu.Stationary.dto;

import lombok.Data;

import java.util.List;

@Data
public class AccountDTO {
    private Long id;
    private String email;
    private String accountPassword;
    private Long userId;
    private String fullName;
    private List<String> roleList;
}
