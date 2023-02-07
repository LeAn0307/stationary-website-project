package com.shinhands.mu.Stationary.repository;

import com.shinhands.mu.Stationary.entity.Account;
import com.shinhands.mu.Stationary.entity.UserWebsite;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserWebsite,Long> {
    List<UserWebsite> findAllByDeletedEquals(Long deleted);

    UserWebsite findByIdEqualsAndDeletedEquals(Long id, Long deleted);
}
