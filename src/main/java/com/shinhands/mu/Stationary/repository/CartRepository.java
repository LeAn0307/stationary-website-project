package com.shinhands.mu.Stationary.repository;

import com.shinhands.mu.Stationary.entity.Cart;
import com.shinhands.mu.Stationary.entity.CartCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
}
