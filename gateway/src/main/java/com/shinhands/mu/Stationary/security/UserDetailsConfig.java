package com.shinhands.mu.Stationary.security;

import com.shinhands.mu.Stationary.config.AuthConfig;
import com.shinhands.mu.Stationary.dto.AccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsConfig implements ReactiveUserDetailsService {

//    private final InMemoryUserDetailsManager inMemoryUserDetailsManager;
//
//    public UserDetailsConfig() {
//        List<UserDetails> users = new ArrayList<>();
//        users.add(User.withDefaultPasswordEncoder()
//                .username("123")
//                .password("123")
//                .roles("USER")
//                .build());
//        this.inMemoryUserDetailsManager = new InMemoryUserDetailsManager(users);
//    }

    @Autowired
    RestTemplate restTemplate;
    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return Mono.fromCallable(() -> restTemplate.getForObject("http://localhost:8099/api/accounts/" + username, AccountDTO.class))
                .map(accountDTO -> new CustomUserDetails(accountDTO.getEmail(), accountDTO.getAccountPassword(),
                        accountDTO.getUserId(), accountDTO.getFullName(),
                        AuthorityUtils.createAuthorityList(accountDTO.getRoleList().toArray(new String[0]))));
    }
}