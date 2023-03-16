package com.shinhands.mu.Stationary.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartProductApiDTO {
    private Long id;
    private String name;
    private Long quantity;
    private Long price;
    private String image;
    private Long avgrating;
    private Long discount;
}
