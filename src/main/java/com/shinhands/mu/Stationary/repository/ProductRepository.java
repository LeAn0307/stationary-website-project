package com.shinhands.mu.Stationary.repository;

import com.shinhands.mu.Stationary.entity.Account;
import com.shinhands.mu.Stationary.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findAllByDeletedEquals(Long deleted);

    Product findByIdEqualsAndDeletedEquals(Long id, Long deleted);
}
