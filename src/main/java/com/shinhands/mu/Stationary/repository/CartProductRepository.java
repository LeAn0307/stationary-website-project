package com.shinhands.mu.Stationary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.shinhands.mu.Stationary.entity.CartProduct;
@Repository
public interface CartProductRepository extends JpaRepository<CartProduct,Long> {
}
