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
    public List<Rating> getAllRatings()
    {
        System.out.println(ratingRepository.findAll());
        return mapper.map(ratingRepository.findAll(), new TypeToken<List<RatingDTO>>(){}.getType());

    }
    public RatingDTO addRating(RatingDTO ratingDTO)
    {
        return mapper.map(ratingRepository.save(mapper.map(ratingDTO,Rating.class)), RatingDTO.class);
    }

    public Boolean deleteRating(long id)
    {
        try{
            ratingRepository.deleteById(id);
            return true;
        }
        catch (Exception e)
        {

            System.out.println(e.getMessage());
            return false;
        }
    }
    public RatingDTO getRatingById(long id)
    {
        return mapper.map(ratingRepository.findById(id).orElse(null), RatingDTO.class);
    }
    public Boolean updateRating(long id, RatingDTO ratingDTO)
    {
        Rating oldRating=ratingRepository.findById(id).orElse(null);
        if(oldRating==null)
        {
            return false;
        }
        else
        {
            ratingRepository.save(mapper.map(ratingDTO,Rating.class));
        }
        return true;
    }
}
