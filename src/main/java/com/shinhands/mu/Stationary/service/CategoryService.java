package com.shinhands.mu.Stationary.service;

import com.shinhands.mu.Stationary.dto.CategoryDTO;
import com.shinhands.mu.Stationary.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoryService {
     public List<Category> getAllCategories();
     public CategoryDTO addCategory(CategoryDTO category);
     public Boolean deleteCategory(long id);
     public CategoryDTO getCategoryById(long id);
     public Boolean updateCategory(long id, CategoryDTO category);
}
