package com.shinhands.mu.Stationary.service.serviceImpl;

import com.shinhands.mu.Stationary.entity.UserRole;
import com.shinhands.mu.Stationary.repository.UserRoleRepository;
import com.shinhands.mu.Stationary.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public Boolean addUserRole(UserRole userRole) {
        if(userRoleRepository.save(userRole) != null) {
            return true;
        }
        return false;
    }
}
