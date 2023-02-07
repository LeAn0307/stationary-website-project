package com.shinhands.mu.Stationary.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Table(name="rating")
@SequenceGenerator(name= "NAME_SEQUENCE", sequenceName = "RATING_SEQ", allocationSize = 1)
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="NAME_SEQUENCE")
    @Column(name="id",nullable=false)
    private Long ratingId;
    @Column(name="comment_product",nullable=false,length=500)
    private String comment;
    @Column(name="ratescore",nullable=false)
    private double rateScore;
    @Column(name="iduser")
    private Long userId;
    @Column(name = "deleted")
    private Long deleted;
//    @Column(name="product_id")
//    private Long productId;
}