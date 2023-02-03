package com.shinhands.mu.Stationary.entity;

import javax.persistence.*;

@Entity
@Table(name = "bill_status")
public class BillStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long billId;
}
