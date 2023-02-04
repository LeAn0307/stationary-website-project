package com.shinhands.mu.Stationary.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="categories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartProduct {
    @Id
    @GeneratedValue
    @Column(name="CartProduct")
    private Long cartProductId;
    @Column
    private int quantity;
    @Column
    private Long productId;
    //@ManyToOne(fetch = FetchType.LAZY, optional = false)
    //@JoinColumn(name = "productId")
    //@EqualsAndHashCode.Exclude
    //@JsonBackReference
   // private Product product;
    @Column
    private Long cartId;
}
