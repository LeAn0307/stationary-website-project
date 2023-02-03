package com.shinhands.mu.Stationary.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "bill")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bill_id")
    private long id;

    @Column(name = "date_order")
    private Date dateOrder;

    @Column(name = "total")
    private long total;

    @Column(name = "note")
    private String note;

    @Column(name = "status")
    private String status;

    @Column(name = "payment")
    private String payment;

    @Column(name = "codemomo")
    private String codemomo;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;
}