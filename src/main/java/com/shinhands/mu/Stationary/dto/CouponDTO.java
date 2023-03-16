package com.shinhands.mu.Stationary.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CouponDTO {

//    @NotNull(message = "ID  phải có giá trị")
    private Long id;

    @NotBlank(message = "Name phải có giá trị")
//    @Size(min = 3, max = 50, message = "Name phải từ 3 - 50 ký tự")
    private String name;


    @Min(value = 0, message = "Discount phai >= 0")
    private double discount;

    @PositiveOrZero(message = "Minimum price phải >= 0")
    private double minPrice;


    @Positive(message = "Maximum price  phải > 0")
    private double maxPrice;

    @NotBlank(message = "Code phải có giá trị")
    private String code;


    @Positive(message = "Amount phải > 0")
    private double amount;

}
