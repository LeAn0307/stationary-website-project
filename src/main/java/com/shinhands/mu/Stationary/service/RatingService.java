package com.shinhands.mu.Stationary.service;

import com.shinhands.mu.Stationary.dto.RatingDTO;
import com.shinhands.mu.Stationary.entity.Rating;
import java.util.List;
public interface RatingService {
    public List<RatingDTO> getAllRatings();
    public RatingDTO addRating(RatingDTO ratingDTO);
    public Boolean deleteRating(long id);
    public RatingDTO getRatingById(long id);
    public Boolean updateRating(long id, RatingDTO ratingDTO);
    public List<RatingDTO> getAllRatingsByProductID(Long productId);
}
