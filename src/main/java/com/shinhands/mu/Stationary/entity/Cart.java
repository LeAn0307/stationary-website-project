package com.shinhands.mu.Stationary.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import java.math.BigDecimal;
@Entity
@Table(name="cart")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue
    @Column(name = "cartId",nullable = false)
    private Long cartId;
    @Column(name = "total")
    private BigDecimal total;
    /*@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonManagedReference
    private List<CartProduct> cartProducts=new ArrayList<>();
    */
}
