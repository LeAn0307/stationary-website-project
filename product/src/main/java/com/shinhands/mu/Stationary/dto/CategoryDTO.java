package com.shinhands.mu.Stationary.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private Long id;

    @NotBlank(message = "Tên danh mục không được trống")
    private String categoriesName;

    @NotBlank(message = "Hình ảnh không được trống")
    private String image;
}
