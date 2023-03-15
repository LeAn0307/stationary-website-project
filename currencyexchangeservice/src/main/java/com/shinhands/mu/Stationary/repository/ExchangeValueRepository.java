package com.shinhands.mu.Stationary.repository;

import com.shinhands.mu.Stationary.model.ExchangeValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeValueRepository extends JpaRepository<ExchangeValue,Long> {
        ExchangeValue findByFromAndTo(String from,String to );
}
