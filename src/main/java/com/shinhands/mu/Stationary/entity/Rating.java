package com.shinhands.mu.Stationary.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="rating")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
    @Id
    @GeneratedValue
    @Column(name="ratingId",nullable=false)
    private Long ratingId;
    @Column(name="comment",nullable=false,length=500)
    private String comment;
    @Column(name="ratescore",nullable=false)
    private double rateScore;
    @Column
    private Long userId;

}
