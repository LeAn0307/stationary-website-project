package com.shinhands.mu.Stationary.security;

import com.shinhands.mu.Stationary.dto.UserDTO;
import com.shinhands.mu.Stationary.entity.Account;
import com.shinhands.mu.Stationary.entity.Role;
import com.shinhands.mu.Stationary.repository.AccountRepository;
import com.shinhands.mu.Stationary.repository.RoleRepoMybatis;
import com.shinhands.mu.Stationary.repository.UserLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private AccountRepository _accountRepository;

    @Autowired
    private UserLoginRepository userLoginRepository;
    @Autowired
    private RoleRepoMybatis roleRepoMybatis;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = _accountRepository.findByEmailAndDeleted(email, 0L);
        if (account == null) {
            throw new UsernameNotFoundException(email);
        }

        UserDTO user = userLoginRepository.getUserByAccount(account.getEmail(), account.getAccountPassword());

        List<Role> roles = roleRepoMybatis.getRolesByAccountId(account.getId());

        List<GrantedAuthority> authorities = new ArrayList<>();
        for(Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getNameRole()));
        }
        return new CustomUserDetails(account, user.getId(), user.getUserName() ,authorities);
    }
}
