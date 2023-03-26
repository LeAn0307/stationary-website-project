package com.shinhands.mu.Stationary.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HistoryOrderDTO {
    private Long billId;

    private Date dateOrder;

    private Long price;

    private int quantity;

    private String productName;

    private String productImage;

    private String productColor;

    private String status;
}
