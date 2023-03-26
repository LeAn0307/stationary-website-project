//package com.shinhands.mu.Stationary.service.serviceImpl;
//
//import com.shinhands.mu.Stationary.entity.Account;
//import com.shinhands.mu.Stationary.entity.AccountDetails;
//import com.shinhands.mu.Stationary.repository.AccountRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AccountDetailsServiceImpl implements UserDetailsService {
//    @Autowired
//    private AccountRepository _accountRepository;
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Account account = _accountRepository.findByEmail(email);
//        if (account == null) {
//            throw new UsernameNotFoundException(email);
//        }
//        return new AccountDetails(account);
//    }
//}
