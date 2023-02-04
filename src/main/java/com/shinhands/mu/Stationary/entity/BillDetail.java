package com.shinhands.mu.Stationary.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "bill_detail")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BillDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private long price;

    @Column(name = "createdat")
    private Date createdAt;

    @Column(name = "updateat")
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "id_bill", nullable = false, referencedColumnName = "bill_id")
    private Bill bill;

//    @ManyToOne
//    @JoinColumn(name = "id_product", nullable = false, referencedColumnName = "id")
//    private Product product;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "id_rating", referencedColumnName = "id")
//    @JsonManagedReference
//    private Rating rating;
}
