//package com.shinhands.mu.Stationary.entity;
//
//import com.shinhands.mu.Stationary.entity.Account;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.Collections;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class AccountDetails implements UserDetails {
//    private Account _account;
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
//    }
//
//    @Override
//    public String getPassword() {
//        return _account.getAccountPassword();
//    }
//
//    @Override
//    public String getUsername() {
//        return _account.getEmail();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
