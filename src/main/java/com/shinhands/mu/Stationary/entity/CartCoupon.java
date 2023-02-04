package com.shinhands.mu.Stationary.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="cart_coupon")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartCoupon {
    @Id
    @GeneratedValue
    private Long cartCouponId;
    @Column
    private Long cartId;
    @Column
    private Long couponId;
}
