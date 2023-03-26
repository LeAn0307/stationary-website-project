package com.shinhands.mu.Stationary.controller;

import com.shinhands.mu.Stationary.dto.CategoryDTO;

import com.shinhands.mu.Stationary.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


@RestController("")
@RequestMapping(value="/category")
public class CategoryController {
    @Autowired
    CategoryService CategoryService;
    @RequestMapping(value="",method= RequestMethod.GET)
    public ResponseEntity getCategory()
    {
        return ResponseEntity.ok().body(CategoryService.getAllCategories());
    }
    @PostMapping()
    public ResponseEntity<CategoryDTO> addCategory(@RequestBody CategoryDTO categoryDTO)
    {
        return ResponseEntity.ok().body(CategoryService.addCategory(categoryDTO));
    }
    @GetMapping(value="/{id}")
    public ResponseEntity getCategoryById(@PathVariable(name="id") long id)
    {
        return ResponseEntity.ok().body(CategoryService.getCategoryById(id));
    }
    @PutMapping(value="/{id}")
    public ResponseEntity updateCategory(@PathVariable(name="id") long id,@RequestBody CategoryDTO categoryDTO)
    {
        return ResponseEntity.ok().body(CategoryService.updateCategory(id,categoryDTO));
    }
    @DeleteMapping(value="/{id}")
    public ResponseEntity deleteCategory(@PathVariable(name="id") long id)
    {
        return ResponseEntity.ok().body(CategoryService.deleteCategory(id));
    }
}
