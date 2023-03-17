package com.shinhands.mu.Stationary.service;

import com.shinhands.mu.Stationary.dto.RoleDTO;
import com.shinhands.mu.Stationary.entity.Role;

import java.util.List;

public interface RoleService {
    List<RoleDTO> getRolesByAccountId(Long id);
}
