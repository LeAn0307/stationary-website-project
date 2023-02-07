package com.shinhands.mu.Stationary.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DiscountProductsDTO {

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
