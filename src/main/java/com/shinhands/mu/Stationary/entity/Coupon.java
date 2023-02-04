package com.shinhands.mu.Stationary.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="coupon")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Coupon {
   @Id
   @GeneratedValue
   @Column(name = "couponId",nullable = false)
   private Long couponId;
   @Column(name="name",nullable=false,length=100)
   private String name;
   @Column(name="discount",nullable=false)
   private double discount;
}
