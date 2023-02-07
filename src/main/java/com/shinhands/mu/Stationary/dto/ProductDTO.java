package com.shinhands.mu.Stationary.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private String type;
    private Long discount;
    private Long avgrating;
    private String material;
    private Long categoryid;
    private Long height;
    private Long width;
    private Long weight;
    private String description;
    private String image;
    private String brand;
    private String madein;
    private Long amount;
    private String color;
}
