package com.shinhands.mu.Stationary.service;

import com.shinhands.mu.Stationary.dto.CartDTO;
import com.shinhands.mu.Stationary.entity.Cart;
import java.util.List;
public interface CartService {
    public List<CartDTO> getAllCarts();
    public CartDTO addCart(CartDTO cartDTO);
    public Boolean deleteCart(long id);
    public CartDTO getCartById(long id);
    public Boolean updateCart(long id, CartDTO cartDTO);
}
