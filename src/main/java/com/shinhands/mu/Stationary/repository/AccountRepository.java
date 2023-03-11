package com.shinhands.mu.Stationary.repository;

import com.shinhands.mu.Stationary.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
    List<Account> findAllByDeletedEquals(Long deleted);
    Account findByIdEqualsAndDeletedEquals(Long id, Long deleted);
    Account findByEmailAndDeleted(String email, Long deleted);
}
