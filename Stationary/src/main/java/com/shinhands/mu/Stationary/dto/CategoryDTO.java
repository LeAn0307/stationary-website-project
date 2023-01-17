package com.shinhands.mu.Stationary.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class CategoryDTO {
    private Long categoriesId;
    private String CategoriesName;
    private String image;
}
