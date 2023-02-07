package com.shinhands.mu.Stationary.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CartCouponDTO {
    private long cartCouponId;
    private long cartId;
    private long couponId;
}
