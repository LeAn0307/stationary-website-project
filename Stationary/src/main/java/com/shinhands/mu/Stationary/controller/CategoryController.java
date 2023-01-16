package com.shinhands.mu.Stationary.controller;

import com.shinhands.mu.Stationary.entity.Category;
import com.shinhands.mu.Stationary.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("")
    public ResponseEntity addCategory(@RequestBody Category category)
    {
        return ResponseEntity.ok().body(CategoryService.addCategory(category));
    }
    @GetMapping(value="/{id}")
    public ResponseEntity getCategoryById(@PathVariable(name="id") long id)
    {
        return ResponseEntity.ok().body(CategoryService.getCategoryById(id));
    }
    @PutMapping(value="/{id}")
    public ResponseEntity updateCategory(@PathVariable(name="id") long id,@RequestBody Category category)
    {
        return ResponseEntity.ok().body(CategoryService.updateCategory(id,category));
    }
    @DeleteMapping(value="/{id}")
    public ResponseEntity deleteCategory(@PathVariable(name="id") long id,@RequestBody Category category)
    {
        return ResponseEntity.ok().body(CategoryService.updateCategory(id,category));
    }
}
