package com.shinhands.mu.Stationary.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;


@Table(name="cart_product")
@SequenceGenerator(name= "NAME_SEQUENCE", sequenceName = "CART_PRODUCT_SEQ", allocationSize = 1)
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartProduct {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="cart_product_gen")
    @Column(name="id")
    private Long id;
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

    @Column(name = "deleted")
    private Long deleted = 0L;
}
