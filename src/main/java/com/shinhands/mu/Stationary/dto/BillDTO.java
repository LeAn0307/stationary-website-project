package com.shinhands.mu.Stationary.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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

    private String status;

    private List<BillDetailDTO> billDetailDTOList;
}
