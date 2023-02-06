package com.shinhands.mu.Stationary.repository;

import com.shinhands.mu.Stationary.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends JpaRepository<Coupon,Long> {
}
