package com.shinhands.mu.Stationary.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="categories")
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue
    @Column(name="categoriesid",nullable = false)
    private Long categoriesId;
    @Column(name="categories_name",nullable = false,length = 100)
    private String CategoriesName;
    @Column(name = "image",nullable = false,length = 100)
    private String image;

}
