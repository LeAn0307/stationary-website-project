package com.shinhands.mu.Stationary.entity;

import com.shinhands.mu.Stationary.dto.BillDTO;
import com.shinhands.mu.Stationary.dto.HistoryOrderDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "bill")
@NamedNativeQuery(
        name = "find_all_bill",
        query = "select b.*, bs.status, u.user_name\n" +
                "from bill b\n" +
                "join bill_status bs on b.id_bill_status = bs.id\n" +
                "join user_website u on b.customer_id = u.id\n" +
                "where b.deleted = 0\n" +
                "order by b.bill_id asc",
        resultSetMapping = "bill_dto"
)
@NamedNativeQuery(
        name = "find_bill_by_id",
        query = "select b.*, bs.status, u.user_name\n" +
                "from bill b\n" +
                "join bill_status bs on b.id_bill_status = bs.id\n" +
                "join user_website u on b.customer_id = u.id\n" +
                "where b.deleted = 0 and b.bill_id = ?1\n",
        resultSetMapping = "bill_dto"
)
@SqlResultSetMapping(
        name = "bill_dto",
        classes = @ConstructorResult(
                targetClass = BillDTO.class,
                columns = {
                        @ColumnResult(name = "bill_id", type = Long.class),
                        @ColumnResult(name = "date_order", type = Date.class),
                        @ColumnResult(name = "total", type = BigDecimal.class),
                        @ColumnResult(name = "note", type = String.class),
                        @ColumnResult(name = "payment", type = String.class),
                        @ColumnResult(name = "codemomo", type = String.class),
                        @ColumnResult(name = "created_at", type = Date.class),
                        @ColumnResult(name = "updated_at", type = Date.class),
                        @ColumnResult(name = "id_bill_status", type = Long.class),
                        @ColumnResult(name = "customer_id", type = Long.class),
                        @ColumnResult(name = "status", type = String.class),
                        @ColumnResult(name = "user_name", type = String.class)
                }
        )
)
@SequenceGenerator(name= "NAME_SEQUENCE", sequenceName = "BILL_SEQ", allocationSize = 1)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
