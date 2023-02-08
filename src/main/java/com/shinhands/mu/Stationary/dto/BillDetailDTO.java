package com.shinhands.mu.Stationary.dto;

import lombok.*;

import javax.persistence.Column;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BillDetailDTO {
    private long id;

    private int quantity;

    private long price;

    private Date createdAt;

    private Date updatedAt;

    private Long idBill;

    private Long idProduct;
}
