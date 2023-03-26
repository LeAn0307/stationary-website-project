package com.shinhands.mu.Stationary.repository;

import com.shinhands.mu.Stationary.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository< Rating,Long> {
List<Rating> findAllByDeletedEquals(Long deleted);
Rating findByIdEqualsAndDeletedEquals(Long id,Long deleted);
    List<Rating>findByProductIdEqualsAndDeletedEquals(Long productId,Long deleted);
}
