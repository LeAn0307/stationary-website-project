package com.shinhands.mu.Stationary.service;

import com.shinhands.mu.Stationary.dto.AccountDTO;
import com.shinhands.mu.Stationary.dto.UserDTO;

import java.util.List;

public interface AccountService {
    public List<AccountDTO> getAllAccounts();
    public AccountDTO addAccount(AccountDTO accountDTO);
    public Boolean deleteAccount(long id);
    public AccountDTO getAccountById(long id);
    public Boolean updateAccount(long id, AccountDTO accountDTO);
}
