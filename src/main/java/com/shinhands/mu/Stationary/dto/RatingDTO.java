package com.shinhands.mu.Stationary.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RatingDTO {
    private Long id;
    private String comment;
    private double rateScore;
    private Long userId;
    private Long productId;
}