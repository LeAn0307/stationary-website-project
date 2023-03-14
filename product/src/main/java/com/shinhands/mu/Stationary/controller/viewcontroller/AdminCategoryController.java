package com.shinhands.mu.Stationary.controller.viewcontroller;

import com.shinhands.mu.Stationary.config.FileUploadUtil;
import com.shinhands.mu.Stationary.dto.CategoryDTO;
import com.shinhands.mu.Stationary.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping(value = "admin")
public class AdminCategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping(value = "category")
    public ModelMap getAllCategory(ModelMap model) {
        List<CategoryDTO> categories= categoryService.getAllCategories();
        categories.sort(Comparator.comparing(categoryDTO1 -> categoryDTO1.getId()));
        CategoryDTO categoryDTO=new CategoryDTO();
        model.addAttribute("categories",categories);
        model.addAttribute("categoryDTO", categoryDTO);
        return model;
    }
    @GetMapping(value = "category/{id}")
    public String getCategoryById(@PathVariable(name="id") long id,Model model) {
        CategoryDTO categoryDTO=categoryService.getCategoryById(id);
        model.addAttribute("category",categoryDTO);
        return "admin/edit-category";
    }
    @RequestMapping(path = "/category/add", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String addCategory(@RequestParam("photo") MultipartFile photo,CategoryDTO categoryDTO,Model model) {
        String fileName = StringUtils.cleanPath(photo.getOriginalFilename());
        categoryDTO.setImage(fileName);
        String upload = "src/main/resources/static/images/category";
        try {
            FileUploadUtil.saveFile(upload, fileName, photo);
            categoryService.addCategory(categoryDTO);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/admin/category";
    }
    @RequestMapping(path = "/category/update", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String updateProduct(@RequestParam("photo") MultipartFile photo, CategoryDTO categoryDTO, Model model) {
        String fileName = StringUtils.cleanPath(photo.getOriginalFilename());
        if(!fileName.equals("")) {
            categoryDTO.setImage(fileName);
            String upload = "src/main/resources/static/images/category";
            try {
                FileUploadUtil.saveFile(upload, fileName, photo);
                categoryService.updateCategory(categoryDTO.getId(), categoryDTO);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            categoryService.updateCategory(categoryDTO.getId(), categoryDTO);
        }
        return "redirect:/admin/category";
    }
    @GetMapping("delete-category/{id}")
    public String deleteCategory(@PathVariable Long id, Model model) {
        categoryService.deleteCategory(id);
        return "redirect:/admin/category";
    }

}
