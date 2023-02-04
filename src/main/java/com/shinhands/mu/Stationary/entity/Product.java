package com.shinhands.mu.Stationary.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue
    @Column(name="id",nullable = false)
    private Long id;
    @Column(name = "name",length = 255)
    private String name;
    @Column(name = "type",length = 255)
    private String type;
    @Column(name = "discount")
    private Long discount;
    @Column(name = "avgrating")
    private Long avgrating;
    @Column(name = "material",length = 255)
    private String material;
    @Column(name = "categoryid")
    private Long categoryid;
    @Column(name = "height")
    private Long height;
    @Column(name = "width")
    private Long width;
    @Column(name = "weight")
    private Long weight;
    @Column(name = "description",length = 255)
    private String description;
    @Column(name = "image",length = 255)
    private String image;
    @Column(name = "brand",length = 255)
    private String brand;
    @Column(name = "madein",length = 255)
    private String madein;
    @Column(name = "amount")
    private Long amount;
    @Column(name = "color",length = 255)
    private String color;
}
