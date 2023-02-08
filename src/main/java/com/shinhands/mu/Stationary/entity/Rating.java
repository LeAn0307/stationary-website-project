package com.shinhands.mu.Stationary.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Table(name="rating")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
    @Id
    @GeneratedValue
    @Column(name="id",nullable=false)
    private Long id;
    @Column(name="comment_product",nullable=false,length=500)
    private String comment;
    @Column(name="ratescore",nullable=false)
    private double rateScore;
    @Column(name="iduser")
    private Long userId;
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "deleted")
    private Long deleted;
}