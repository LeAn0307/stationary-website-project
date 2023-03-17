package com.shinhands.mu.Stationary.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillResponseDTO {
    private Long id;

    private Date dateOrder;

    private BigDecimal total;

    private String note;

    private String payment;

    private String codeMomo;

    private Date createdAt;

    private Date updatedAt;

    private String status;

    private String customerName;

    private List<BillDetailDTO> billDetailList;
}
