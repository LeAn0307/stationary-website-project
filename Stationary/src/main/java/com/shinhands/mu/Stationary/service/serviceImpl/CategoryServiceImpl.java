package com.shinhands.mu.Stationary.service.serviceImpl;

import com.shinhands.mu.Stationary.dto.CategoryDTO;
import com.shinhands.mu.Stationary.entity.Category;
import com.shinhands.mu.Stationary.repository.CategoryRepository;
import com.shinhands.mu.Stationary.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper mapper;
    public List<Category> getAllCategories()
    {

        return categoryRepository.findAll();
    }
    public List<Category> addCategory(CategoryDTO categoryDTO)
    {
        categoryRepository.save(mapper.map(categoryDTO,Category.class));
        return categoryRepository.findAll();
    }

    public Boolean deleteCategory(long id)
    {
        try{
            categoryRepository.deleteById(id);
            return true;
        }
        catch (Exception e)
        {

            System.out.println(e.getMessage());
            return false;
        }
    }
    public Category getCategoryById(long id)
    {
        return categoryRepository.findById(id).orElse(null);
    }
    public Boolean updateCategory(long id, CategoryDTO categoryDTO)
    {
        Category oldCategory=categoryRepository.findById(id).orElse(null);
        if(oldCategory==null)
        {
            return false;
        }
        else
        {
             categoryRepository.save(mapper.map(categoryDTO,Category.class));
        }
        return true;
    }
}
