package com.shinhands.mu.Stationary.service;
import com.shinhands.mu.Stationary.dto.CouponDTO;

import java.util.List;
public interface CouponService {
    public List<CouponDTO> getAllCoupons();
    public CouponDTO addCoupon(CouponDTO couponDTO);
    public Boolean deleteCoupon(long id);
    public CouponDTO getCouponById(long id);
    public Boolean updateCoupon(long id, CouponDTO couponDTO);
    public CouponDTO getCartByCode(String code, long deleted);
}
