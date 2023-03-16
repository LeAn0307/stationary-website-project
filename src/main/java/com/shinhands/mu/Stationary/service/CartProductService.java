package com.shinhands.mu.Stationary.service;

import com.shinhands.mu.Stationary.dto.CartProductDTO;

import java.util.List;
public interface CartProductService {
    public List<CartProductDTO> getAllCartProducts();

    public Boolean addCartProduct(CartProductDTO cartProductDTO);
    public Boolean deleteCartProduct(long id);
    public CartProductDTO getCartProductById(long id);
    public Boolean updateCartProduct(long id, CartProductDTO cartProductDTO);
    public Long countProductIdInCart(Long cardId);
}
