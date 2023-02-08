package com.shinhands.mu.Stationary.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
@SequenceGenerator(name= "NAME_SEQUENCE", sequenceName = "CATEGORIES_SEQ", allocationSize = 1)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Categories {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="NAME_SEQUENCE")
    @Column(name="categoriesid",nullable = false)
    private Long categoriesId;
    @Column(name="categories_name",nullable = false,length = 100)
    private String categoriesName;
    @Column(name = "image",nullable = false,length = 100)
    private String image;

    @Column(name = "deleted")
    private Long deleted;

//    @OneToMany(mappedBy = "categories", cascade = CascadeType.ALL)
//    private List<Product> productList;
}
