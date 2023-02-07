package com.shinhands.mu.Stationary.service.serviceImpl;

import com.shinhands.mu.Stationary.dto.AccountDTO;
import com.shinhands.mu.Stationary.entity.Account;
import com.shinhands.mu.Stationary.repository.AccountRepository;
import com.shinhands.mu.Stationary.service.AccountService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    private ModelMapper mapper;
    @Override
    public List<AccountDTO> getAllAccounts() {
        return mapper.map(accountRepository.findAllByDeletedEquals(0L), new TypeToken<List<AccountDTO>>(){}.getType());
    }

    @Override
    public AccountDTO addAccount(AccountDTO accountDTO) {
        return mapper.map(accountRepository.save(mapper.map(accountDTO, Account.class)), AccountDTO.class);
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
