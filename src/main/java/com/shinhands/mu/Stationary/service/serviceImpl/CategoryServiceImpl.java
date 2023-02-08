package com.shinhands.mu.Stationary.service.serviceImpl;

import com.shinhands.mu.Stationary.dto.CategoryDTO;
import com.shinhands.mu.Stationary.entity.Category;
import com.shinhands.mu.Stationary.repository.CategoryRepository;
import com.shinhands.mu.Stationary.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper mapper;
    public List<CategoryDTO> getAllCategories()
    {
        return mapper.map(categoryRepository.findAll(), new TypeToken<List<CategoryDTO>>(){}.getType());
    }
    public CategoryDTO addCategory(CategoryDTO categoryDTO)
    {
        return mapper.map(categoryRepository.save(mapper.map(categoryDTO,Category.class)), CategoryDTO.class);
    }

    public Boolean deleteCategory(long id)
    {
        Category oldCategory=categoryRepository.findById(id).orElse(null);
        if (oldCategory!=null){
            oldCategory.setDeleted(1L);
            categoryRepository.save(oldCategory);
            return true;
        } else return false;
    }
    public CategoryDTO getCategoryById(long id)
    {
        Category oldCategory=categoryRepository.findById(id).orElse(null);
        if( oldCategory!=null)
        return mapper.map(oldCategory, CategoryDTO.class);
        else return null;
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
