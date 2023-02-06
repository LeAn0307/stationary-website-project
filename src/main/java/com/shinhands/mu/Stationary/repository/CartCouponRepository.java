package com.shinhands.mu.Stationary.repository;

import com.shinhands.mu.Stationary.entity.CartCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartCouponRepository extends JpaRepository<CartCoupon,Long> {
}
