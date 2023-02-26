package com.shinhands.mu.Stationary.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.shinhands.mu.Stationary.dto.HistoryOrderDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "bill_detail")
@NamedNativeQuery(
        name = "find_all_order_one_customer",
        query = "SELECT b.bill_id as billId, b.date_order as dateOrder, bd.price as price, p.name as productName, " +
                "bd.quantity as quantity, p.image as image, p.color as color, bs.status as status " +
                "FROM bill_detail bd " +
                "JOIN bill b ON bd.id_bill = b.bill_id " +
                "JOIN bill_status bs ON b.id_bill_status = bs.id " +
                "JOIN product p ON bd.id_product = p.id " +
                "WHERE b.customer_id = ?1 ",
        resultSetMapping = "history_order_dto"
)
@SqlResultSetMapping(
        name = "history_order_dto",
        classes = @ConstructorResult(
                targetClass = HistoryOrderDTO.class,
                columns = {
                        @ColumnResult(name = "billId", type = Long.class),
                        @ColumnResult(name = "dateOrder", type = Date.class),
                        @ColumnResult(name = "price", type = Long.class),
                        @ColumnResult(name = "quantity", type = Integer.class),
                        @ColumnResult(name = "productName", type = String.class),
                        @ColumnResult(name = "image", type = String.class),
                        @ColumnResult(name = "color", type = String.class),
                        @ColumnResult(name = "status", type = String.class)
                }
        )
)
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

    @Column(name = "ID_BILL")
    private Long idBill;

    @Column(name = "id_product")
    private Long idProduct;

    @Column(name = "deleted")
    private Long deleted = 0L;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_bill", nullable = false, referencedColumnName = "bill_id")
//    private Bill bill;

//    @ManyToOne
//    @JoinColumn(name = "id_product", nullable = false, referencedColumnName = "id")
//    private Product product;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "id_rating", referencedColumnName = "id")
//    @JsonManagedReference
//    private Rating rating;
}
