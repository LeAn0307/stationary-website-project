package com.shinhands.mu.Stationary.service;
import com.shinhands.mu.Stationary.dto.CategoryDTO;
import com.shinhands.mu.Stationary.dto.CouponDTO;
import com.shinhands.mu.Stationary.entity.Category;
import com.shinhands.mu.Stationary.entity.Coupon;

import java.util.List;
public interface CouponService {
    public List<Coupon> getAllCoupons();
    public CouponDTO addCoupon(CouponDTO category);
    public Boolean deleteCoupon(long id);
    public CouponDTO getCouponById(long id);
    public Boolean updateCoupon(long id, CouponDTO category);
}
