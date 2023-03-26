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
@SequenceGenerator(name= "CATEGORIES_SEQ", sequenceName = "CATEGORIES_SEQ", initialValue=1, allocationSize = 1)
public class Category {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CATEGORIES_SEQ")
    @Column(name="categoriesid",nullable = false)
    private Long id;
    @Column(name="categories_name",nullable = false,length = 100)
    private String categoriesName;
    @Column(name = "image",nullable = false,length = 100)
    private String image;

    @Column(name = "deleted")
    private Long deleted = 0L;
}
