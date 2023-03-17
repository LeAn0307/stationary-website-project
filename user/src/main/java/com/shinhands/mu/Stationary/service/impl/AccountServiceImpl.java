package com.shinhands.mu.Stationary.service.impl;

import com.shinhands.mu.Stationary.config.HashPasswordConfig;
import com.shinhands.mu.Stationary.dto.AccountDTO;
import com.shinhands.mu.Stationary.dto.RoleDTO;
import com.shinhands.mu.Stationary.dto.UserDTO;
import com.shinhands.mu.Stationary.entity.Account;
import com.shinhands.mu.Stationary.entity.Role;
import com.shinhands.mu.Stationary.entity.UserWebsite;
import com.shinhands.mu.Stationary.repository.AccountRepository;
import com.shinhands.mu.Stationary.repository.RoleRepoMybatis;
import com.shinhands.mu.Stationary.repository.UserLoginRepository;
import com.shinhands.mu.Stationary.repository.UserRepository;
import com.shinhands.mu.Stationary.service.AccountService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    private ModelMapper mapper;

    @Resource
    private RoleRepoMybatis roleRepoMybatis;

    @Autowired
    private UserLoginRepository userLoginRepository;


    @Autowired
    private HashPasswordConfig _hashPasswordConfig;

    @Override
    public boolean authentication(AccountDTO accountDTO) {
        Account account = accountRepository.findByEmailAndDeleted(accountDTO.getEmail(), 0L);
        if (account == null) {
            return false;
        }
        if (_hashPasswordConfig.isMatch(accountDTO.getAccountPassword(), account.getAccountPassword())) {
            return true;
        }
        return false;
    }

    @Override
    public AccountDTO getAccountByEmail(String email) {
        AccountDTO accountDTO = mapper.map(accountRepository.findByEmailAndDeleted(email, 0L), AccountDTO.class);
        if(accountDTO != null) {
            UserDTO userDTO = userLoginRepository.getUserByAccount(accountDTO.getEmail(), accountDTO.getAccountPassword());
            List<RoleDTO> roleLists = roleRepoMybatis.getRolesByAccountId(accountDTO.getId());
            accountDTO.setUserId(userDTO.getId());
            accountDTO.setRoleList(roleLists);
        }
        return accountDTO;
    }

    @Override
    public List<AccountDTO> getAllAccounts() {
        return mapper.map(accountRepository.findAllByDeletedEquals(0L), new TypeToken<List<AccountDTO>>(){}.getType());
    }

    @Override
    public Boolean isExistAccount(String email) {
        Account existAccount = accountRepository.findByEmailAndDeleted(email, 0L);
        if(existAccount == null) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean deleteAccountByEmail(String email) {
        return accountRepository.deleteAccountByEmail(email);
    }

    @Override
    public AccountDTO addAccount(AccountDTO accountDTO) {
        String encodedPassword = _hashPasswordConfig.encode(accountDTO.getAccountPassword());
        accountDTO.setAccountPassword(encodedPassword);
        Account account = mapper.map(accountDTO, Account.class);
        account.setDeleted(0L);
        return mapper.map(accountRepository.save(account), AccountDTO.class);
    }

    @Override
    public Boolean deleteAccount(long id) {
        Account oldAccount = accountRepository.findByIdEqualsAndDeletedEquals(id,0L);
        if (oldAccount!=null){
            oldAccount.setDeleted(1L);
            accountRepository.save(oldAccount);
            return true;
        } else return false;
    }

    @Override
    public AccountDTO getAccountById(long id) {
        Account oldAccount=accountRepository.findByIdEqualsAndDeletedEquals(id,0L);
        if (oldAccount!=null)
            return mapper.map(oldAccount, AccountDTO.class);
        else return null;
    }

    @Override
    public Boolean updateAccount(long id, AccountDTO accountDTO) {
        Account oldAccount=accountRepository.findByIdEqualsAndDeletedEquals(id,0L);
        if(oldAccount==null )
        {
            return false;
        }
        else
        {
            accountRepository.save(mapper.map(accountDTO,Account.class));
        }
        return true;
    }
}
