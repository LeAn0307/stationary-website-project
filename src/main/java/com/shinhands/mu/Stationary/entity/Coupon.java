package com.shinhands.mu.Stationary.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="coupon")
@SequenceGenerator(name= "NAME_SEQUENCE", sequenceName = "COUPON_SEQ", allocationSize = 1)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Coupon {
   @Id
   @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="NAME_SEQUENCE")
   @Column(name = "ID",nullable = false)
   private Long id;
   @Column(name="name",nullable=false,length=100)
   private String name;
   @Column(name="discount",nullable=false)
   private double discount;
   @Column(name="MIN_PRICE_APPLY")
   private double minPrice;
   @Column(name="MAX_PRICE")
   private double maxPrice;
   @Column(name="code",length=20)
   private String code;
   @Column(name="amount")
   private double amount;
   @Column(name = "deleted")
   private Long deleted = 0L;
}
