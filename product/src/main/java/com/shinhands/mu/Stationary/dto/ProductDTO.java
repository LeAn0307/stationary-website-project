package com.shinhands.mu.Stationary.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long     id;
    @NotBlank(message = "Tên sản phẩm không được trống")
    @Size(max = 255, message = "Tên sản phẩm không quá 255 ký tự")
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
    private BigDecimal price;
    private String categoriesName;
}
