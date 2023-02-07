package com.shinhands.mu.Stationary.service;

import com.shinhands.mu.Stationary.dto.CartDTO;
import com.shinhands.mu.Stationary.entity.Cart;
import java.util.List;
public interface CartService {
    public List<Cart> getAllCarts();
    public CartDTO addCart(CartDTO category);
    public Boolean deleteCart(long id);
    public CartDTO getCartById(long id);
    public Boolean updateCart(long id, CartDTO category);
}
