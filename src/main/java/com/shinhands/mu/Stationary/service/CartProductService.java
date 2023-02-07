package com.shinhands.mu.Stationary.service;

import com.shinhands.mu.Stationary.dto.CartProductDTO;
import com.shinhands.mu.Stationary.entity.CartProduct;
import java.util.List;
public interface CartProductService {
    public List<CartProduct> getAllCartProducts();
    public CartProductDTO addCartProduct(CartProductDTO category);
    public Boolean deleteCartProduct(long id);
    public CartProductDTO getCartProductById(long id);
    public Boolean updateCartProduct(long id, CartProductDTO category);
}
