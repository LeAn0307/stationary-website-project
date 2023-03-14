package com.shinhands.mu.Stationary.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "bill_status")
@SequenceGenerator(name= "NAME_SEQUENCE", sequenceName = "BILL_STATUS_SE", allocationSize = 1)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillStatus {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="NAME_SEQUENCE")
    @Column(name = "id")
    private long id;

    @Column(name = "status", length = 100)
    private String status;

    @Column(name = "deleted")
    private Long deleted = 0L;
//
//    @OneToMany(mappedBy = "billStatus", cascade = CascadeType.ALL)
//    private List<Bill> bill;

}
