package com.shinhands.mu.Stationary.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CouponDTO {
    private Long id;
    private String name;
    private double discount;
    private double minPrice;
    private double maxPrice;
    private String code;
    private double amount;
}
