package com.shinhands.mu.Stationary.service;


import com.shinhands.mu.Stationary.dto.CartProductApiDTO;
import com.shinhands.mu.Stationary.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    public List<ProductDTO> getAllProducts();
    public ProductDTO addProduct(ProductDTO productDTO);
    public Boolean deleteProduct(long id);
    public ProductDTO getProductById(long id);
    public Boolean updateProduct(long id, ProductDTO productDTO);
    public List<CartProductApiDTO> getApiCartProduct(long id);
    public List<CartProductApiDTO> getProductFetch(long offset);
    public int countProduct();
}
