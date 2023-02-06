package com.shinhands.mu.Stationary.repository;

import com.shinhands.mu.Stationary.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository< Rating,Long> {

}
