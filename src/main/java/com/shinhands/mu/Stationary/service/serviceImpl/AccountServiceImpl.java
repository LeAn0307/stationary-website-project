package com.shinhands.mu.Stationary.service.serviceImpl;//package com.shinhands.mu.Stationary.service.serviceImpl;
//
//import com.shinhands.mu.Stationary.config.HashPasswordConfig;
//import com.shinhands.mu.Stationary.dto.AccountDTO;
//import com.shinhands.mu.Stationary.entity.Account;
//import com.shinhands.mu.Stationary.repository.AccountRepository;
//import com.shinhands.mu.Stationary.service.AccountService;
//import org.modelmapper.ModelMapper;
//import org.modelmapper.TypeToken;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//@Service
//public class AccountServiceImpl implements AccountService {
//
//
//    @Autowired
//    AccountRepository accountRepository;
//    @Autowired
//    private ModelMapper mapper;
//    @Autowired
//    private HashPasswordConfig _hashPasswordConfig;
//
//    @Override
//    public boolean authentication(AccountDTO accountDTO) {
//        Account account = accountRepository.findByEmailAndDeleted(accountDTO.getEmail(), 0L);
//        if (account == null) {
//            return false;
//        }
//        if (_hashPasswordConfig.isMatch(accountDTO.getAccountPassword(), account.getAccountPassword())) {
//            return true;
//        }
//        return false;
//    }
//
//    @Override
//    public List<AccountDTO> getAllAccounts() {
//        return mapper.map(accountRepository.findAllByDeletedEquals(0L), new TypeToken<List<AccountDTO>>(){}.getType());
//    }
//
//    @Override
//    public Boolean isExistAccount(String email) {
//        Account existAccount = accountRepository.findByEmailAndDeleted(email, 0L);
//        if(existAccount == null) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public AccountDTO addAccount(AccountDTO accountDTO) {
//        String encodedPassword = _hashPasswordConfig.encode(accountDTO.getAccountPassword());
//        accountDTO.setAccountPassword(encodedPassword);
//        Account account = mapper.map(accountDTO, Account.class);
//        account.setDeleted(0L);
//        return mapper.map(accountRepository.save(account), AccountDTO.class);
//    }
//
//    @Override
//    public Boolean deleteAccount(long id) {
//        Account oldAccount = accountRepository.findByIdEqualsAndDeletedEquals(id,0L);
//        if (oldAccount!=null){
//            oldAccount.setDeleted(1L);
//            accountRepository.save(oldAccount);
//            return true;
//        } else return false;
//    }
//
//    @Override
//    public AccountDTO getAccountById(long id) {
//        Account oldAccount=accountRepository.findByIdEqualsAndDeletedEquals(id,0L);
//        if (oldAccount!=null)
//        return mapper.map(oldAccount, AccountDTO.class);
//        else return null;
//    }
//
//    @Override
//    public Boolean updateAccount(long id, AccountDTO accountDTO) {
//        Account oldAccount=accountRepository.findByIdEqualsAndDeletedEquals(id,0L);
//        if(oldAccount==null )
//        {
//            return false;
//        }
//        else
//        {
//            accountRepository.save(mapper.map(accountDTO,Account.class));
//        }
//        return true;
//    }
//}
