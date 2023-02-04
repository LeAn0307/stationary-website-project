package com.shinhands.mu.Stationary.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "bill_status")
public class BillStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "status", length = 100)
    private String status;

    @OneToMany(mappedBy = "billStatus", cascade = CascadeType.ALL)
    private List<Bill> bill;

}
