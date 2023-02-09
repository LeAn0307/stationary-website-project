package com.shinhands.mu.Stationary.service;


import com.shinhands.mu.Stationary.dto.ProductDTO;
import com.shinhands.mu.Stationary.entity.Product;

import java.util.List;

public interface ProductService {

    public List<ProductDTO> getAllProducts();
    public ProductDTO addProduct(ProductDTO productDTO);
    public Boolean deleteProduct(long id);
    public ProductDTO getProductById(long id);
    public Boolean updateProduct(long id, ProductDTO productDTO);
}
