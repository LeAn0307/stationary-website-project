package com.shinhands.mu.Stationary.service.serviceImpl;

import com.shinhands.mu.Stationary.entity.Product;
import com.shinhands.mu.Stationary.repository.DiscountProductsRepository;
import com.shinhands.mu.Stationary.service.DiscountProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DiscountProductsServiceImpl implements DiscountProductsService {
    @Autowired
    DiscountProductsRepository discountProductsRepository;

    public List<Product> getAllDiscountProduct() {
        return discountProductsRepository.findAll(Sort.by(Sort.Direction.DESC , "discount") );
    }
}
