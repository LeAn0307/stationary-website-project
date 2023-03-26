package com.shinhands.mu.Stationary.service;

import java.util.List;

public interface RoleService {
    List<String> getRolesByAccountId(Long id);
}
