package com.shinhands.mu.Stationary.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;


@Table(name="cart_product")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartProduct {
    @Id
    @GeneratedValue
    @Column(name="id")
    private Long cartProductId;
    @Column(name="quantity")
    private int quantity;
    @Column(name="product_id")
    private Long productId;
    //@ManyToOne(fetch = FetchType.LAZY, optional = false)
    //@JoinColumn(name = "productId")
    //@EqualsAndHashCode.Exclude
    //@JsonBackReference
   // private Product product;
    @Column(name="cart_id")
    private Long cartId;
}
