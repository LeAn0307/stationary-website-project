package com.shinhands.mu.Stationary.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="categories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue
    @Column(name="categoriesid",nullable = false)
    private Long categoriesId;
    @Column(name="categories_name",nullable = false,length = 100)
    private String categoriesName;
    @Column(name = "image",nullable = false,length = 100)
    private String image;

    @Column(name = "deleted")
    private Long deleted;
}
