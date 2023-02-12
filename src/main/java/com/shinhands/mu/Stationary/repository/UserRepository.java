package com.shinhands.mu.Stationary.repository;

import com.shinhands.mu.Stationary.entity.Account;
import com.shinhands.mu.Stationary.entity.UserWebsite;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<UserWebsite,Long> {
    List<UserWebsite> findAllByDeletedEquals(Long deleted);

    UserWebsite findByIdEqualsAndDeletedEquals(Long id, Long deleted);
}
