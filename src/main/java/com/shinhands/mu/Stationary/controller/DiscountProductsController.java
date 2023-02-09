package com.shinhands.mu.Stationary.controller;

import com.shinhands.mu.Stationary.dto.DiscountProductsDTO;
import com.shinhands.mu.Stationary.dto.HistoryOrderDTO;
import com.shinhands.mu.Stationary.entity.Product;
import com.shinhands.mu.Stationary.service.DiscountProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("")
@RequestMapping("/discounts")
public class DiscountProductsController {
    @Autowired
    private DiscountProductsService discountProductsService;

    @GetMapping(value = "")
    public ResponseEntity<List<Product>> getDiscountProducts() {
        return ResponseEntity.ok().body(discountProductsService.getAllDiscountProduct()) ;
    }

}
