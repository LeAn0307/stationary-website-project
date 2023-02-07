package com.shinhands.mu.Stationary.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "bill_status")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "status", length = 100)
    private String status;

    @Column(name = "deleted")
    private Long deleted;
//
//    @OneToMany(mappedBy = "billStatus", cascade = CascadeType.ALL)
//    private List<Bill> bill;

}
