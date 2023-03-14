package com.shinhands.mu.Stationary.repository;

import com.shinhands.mu.Stationary.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    List<Category> findAllByDeletedEquals(Long deleted);
    Category findByIdEqualsAndDeletedEquals(Long id,Long deleted);
}
