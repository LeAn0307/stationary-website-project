package com.shinhands.mu.Stationary.repository;

import com.shinhands.mu.Stationary.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountProductsRepository extends JpaRepository<Product, Long> {

}
