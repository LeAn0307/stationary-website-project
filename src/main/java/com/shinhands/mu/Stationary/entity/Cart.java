package com.shinhands.mu.Stationary.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import java.math.BigDecimal;

@Table(name="cart")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue
    @Column(name = "id",nullable = false)
    private Long cartId;
    @Column(name = "total")
    private BigDecimal total;

    @Column(name = "deleted")
    private Long deleted;
    /*@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonManagedReference
    private List<CartProduct> cartProducts=new ArrayList<>();
    */
}
