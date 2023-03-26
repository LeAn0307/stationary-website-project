package com.shinhands.mu.Stationary.service.serviceImpl;

import com.shinhands.mu.Stationary.dto.RatingDTO;
import com.shinhands.mu.Stationary.entity.Rating;
import com.shinhands.mu.Stationary.repository.RatingRepository;
import com.shinhands.mu.Stationary.service.RatingService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public List<RatingDTO> getAllRatings() {
        return mapper.map(ratingRepository.findAllByDeletedEquals(0L), new TypeToken<List<RatingDTO>>() {
        }.getType());
    }

    @Override public
    RatingDTO addRating(RatingDTO ratingDTO) {
        Rating rating = mapper.map(ratingDTO, Rating.class);
        rating.setDeleted(0L);
        Rating rating1 = ratingRepository.save(rating);
        return mapper.map(rating1, RatingDTO.class);
    }

    @Override
    public
    Boolean deleteRating(long id) {
        Rating oldRating = ratingRepository.findByIdEqualsAndDeletedEquals(id, 0L);
        if (oldRating != null) {
            oldRating.setDeleted(1L);
            ratingRepository.save(oldRating);
            return true;
        } else return false;
    }

    @Override
    public RatingDTO getRatingById(long id) {
        Rating oldRating = ratingRepository.findByIdEqualsAndDeletedEquals(id, 0L);
        if (oldRating != null) return mapper.map(oldRating, RatingDTO.class);
        else return null;
    }

    @Override
    public Boolean updateRating(long id, RatingDTO ratingDTO) {
        Rating oldRating = ratingRepository.findByIdEqualsAndDeletedEquals(id, 0L);
        if (oldRating == null) {
            return false;
        } else {
            ratingRepository.save(mapper.map(ratingDTO, Rating.class));
        }
        return true;
    }
    @Override
    public List<RatingDTO> getAllRatingsByProductID(Long productId)
    {
        return mapper.map(ratingRepository.findByProductIdEqualsAndDeletedEquals(productId,0L), new TypeToken<List<RatingDTO>>() {
        }.getType());
    }
}
