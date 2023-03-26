package com.shinhands.mu.Stationary.service;

import com.shinhands.mu.Stationary.dto.CartCouponDTO;
import com.shinhands.mu.Stationary.entity.CartCoupon;
import java.util.List;
public interface CartCouponService {
    public List<CartCouponDTO> getAllCartCoupons();

    public CartCouponDTO addCartCoupon(CartCouponDTO cartCouponDTO);
    public Boolean deleteCartCoupon(long id);
    public CartCouponDTO getCartCouponById(long id);
    public Boolean updateCartCoupon(long id, CartCouponDTO cartCouponDTO);

}
