package com.shinhands.mu.Stationary.service.serviceImpl;

import com.shinhands.mu.Stationary.dto.CategoryDTO;
import com.shinhands.mu.Stationary.dto.CategoryDTO;
import com.shinhands.mu.Stationary.entity.Category;
import com.shinhands.mu.Stationary.entity.Category;
import com.shinhands.mu.Stationary.repository.CategoryRepository;
import com.shinhands.mu.Stationary.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper mapper;
    @Override
    public List<CategoryDTO> getAllCategories() {
        return mapper.map(categoryRepository.findAllByDeletedEquals(0L), new TypeToken<List<CategoryDTO>>() {
        }.getType());
    }

    @Override public
    CategoryDTO addCategory(CategoryDTO CategoryDTO) {
        Category Category = mapper.map(CategoryDTO, Category.class);
        Category.setDeleted(0L);
        Category Category1 = categoryRepository.save(Category);
        return mapper.map(Category1, CategoryDTO.class);
    }

    @Override
    public
    Boolean deleteCategory(long id) {
        Category oldCategory = categoryRepository.findByIdEqualsAndDeletedEquals(id, 0L);
        if (oldCategory != null) {
            oldCategory.setDeleted(1L);
            categoryRepository.save(oldCategory);
            return true;
        } else return false;
    }

    @Override
    public CategoryDTO getCategoryById(long id) {
        Category oldCategory = categoryRepository.findByIdEqualsAndDeletedEquals(id, 0L);
        if (oldCategory != null) return mapper.map(oldCategory, CategoryDTO.class);
        else return null;
    }

    @Override
    public Boolean updateCategory(long id, CategoryDTO CategoryDTO) {
        Category oldCategory = categoryRepository.findByIdEqualsAndDeletedEquals(id, 0L);
        if (oldCategory == null) {
            return false;
        } else {
            categoryRepository.save(mapper.map(CategoryDTO, Category.class));
        }
        return true;
    }
}
