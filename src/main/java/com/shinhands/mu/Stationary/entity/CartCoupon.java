package com.shinhands.mu.Stationary.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="cart_coupon")
@SequenceGenerator(name= "NAME_SEQUENCE", sequenceName = "CART_COUPON_SEQ", allocationSize = 1)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartCoupon {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="NAME_SEQUENCE")
    @Column(name="id")
    private long cartCouponId;
    @Column(name="cart_id")
    private long cartId;
    @Column(name="coupon_id")
    private long couponId;

    @Column(name = "deleted")
    private Long deleted;
}
