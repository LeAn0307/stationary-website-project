package com.shinhands.mu.Stationary.repository;

import com.shinhands.mu.Stationary.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.shinhands.mu.Stationary.entity.CartProduct;

import java.util.List;

@Repository
public interface CartProductRepository extends JpaRepository<CartProduct,Long> {
    List<CartProduct> findAllByDeletedEquals(Long deleted);
    CartProduct findByIdEqualsAndDeletedEquals(Long id,Long deleted);
}
