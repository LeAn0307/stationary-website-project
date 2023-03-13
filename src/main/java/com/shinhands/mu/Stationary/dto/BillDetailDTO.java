package com.shinhands.mu.Stationary.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BillDetailDTO {
    private long id;

    private int quantity;

    private long price;

    private Long idBill;

    private Long idProduct;
}
