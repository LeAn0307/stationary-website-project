package com.shinhands.mu.Stationary.repository;

import com.shinhands.mu.Stationary.entity.Category;
import com.shinhands.mu.Stationary.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponRepository extends JpaRepository<Coupon,Long> {
    List<Coupon> findAllByDeletedEquals(Long deleted);
    Coupon findByIdEqualsAndDeletedEquals(Long id,Long deleted);
}
