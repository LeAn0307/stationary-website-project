package com.shinhands.mu.Stationary.entity;

import com.shinhands.mu.Stationary.dto.BillDTO;
import com.shinhands.mu.Stationary.dto.HistoryOrderDTO;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "bill")
@SequenceGenerator(name= "NAME_SEQUENCE", sequenceName = "BILL_SEQ", allocationSize = 1)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Bill {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="NAME_SEQUENCE")
    @Column(name = "bill_id")
    private long id;

    @Column(name = "date_order")
    private Date dateOrder;

    @Column(name = "total")
    private long total;

    @Column(name = "note")
    private String note;

    @Column(name = "payment")
    private String payment;

    @Column(name = "codemomo")
    private String codemomo;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "customer_id")
    private Long customer_id;

    @Column(name = "id_bill_status")
    private Long idBillStatus;

    @Column(name = "deleted")
    private Long deleted = 0L;
//    @ManyToOne
////    @JoinColumn(name = "id_bill_status", nullable = false, referencedColumnName = "id")
////    private BillStatus billStatus;
////
//////    @ManyToOne
//////    @JoinColumn(name = "customer_id", nullable = false, referencedColumnName = "id")
//////    privare User user;
////
////    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL)
////    private List<BillDetail> billDetails;
}
