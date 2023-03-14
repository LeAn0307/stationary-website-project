package com.shinhands.mu.Stationary.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name="cart")
@SequenceGenerator(name= "NAME_SEQUENCE", sequenceName = "CART_SEQ", allocationSize = 1)
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CART_SEQUENCE")
    @Column(name = "id",nullable = false)
    private Long id;
    
    @Column(name = "total")
    private BigDecimal total;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "deleted")
    private Long deleted = 0L;
    /*@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonManagedReference
    private List<CartProduct> cartProducts=new ArrayList<>();
    */
}
