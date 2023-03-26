package com.shinhands.mu.Stationary.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RatingDTO1 {
    private Long id;
    private String comment;
    private double rateScore;
    private Long productId;
    private Long userId;
    private String userName;
    private String userImage;

}
