package com.shinhands.mu.Stationary.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BillDTO {
    private long id;

    private Date dateOrder;

    private BigDecimal total;

    private String note;

    private String payment;

    private String codeMomo;

    private Date createdAt;

    private Date updatedAt;

    private Long idBillStatus;

    private Long customerId;
}
