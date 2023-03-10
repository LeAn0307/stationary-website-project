package com.shinhands.mu.Stationary.service.serviceImpl;

import com.shinhands.mu.Stationary.entity.Role;
import com.shinhands.mu.Stationary.repository.RoleRepoMybatis;
import com.shinhands.mu.Stationary.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleRepoMybatis roleRepoMybatis;

    @Override
    public List<Role> getRolesByAccountId(Long id) {
        return roleRepoMybatis.getRolesByAccountId(id);
    }
}
