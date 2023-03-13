package com.shinhands.mu.Stationary.service.serviceImpl;

import com.shinhands.mu.Stationary.dto.CartDTO;
import com.shinhands.mu.Stationary.entity.Cart;
import com.shinhands.mu.Stationary.repository.CartRepository;
import com.shinhands.mu.Stationary.service.CartService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ModelMapper mapper;
    @Override
    public List<CartDTO> getAllCarts() {
        return mapper.map(cartRepository.findAllByDeletedEquals(0L), new TypeToken<List<CartDTO>>() {
        }.getType());
    }

    @Override public
    CartDTO addCart(CartDTO CartDTO) {
        Cart Cart = mapper.map(CartDTO, Cart.class);
        Cart.setDeleted(0L);
        Cart Cart1 = cartRepository.save(Cart);
        return mapper.map(Cart1, CartDTO.class);
    }

    @Override
    public
    Boolean deleteCart(long id) {
        Cart oldCart = cartRepository.findByIdEqualsAndDeletedEquals(id, 0L);
        if (oldCart != null) {
            oldCart.setDeleted(1L);
            cartRepository.save(oldCart);
            return true;
        } else return false;
    }

    @Override
    public CartDTO getCartById(long id) {
        Cart oldCart = cartRepository.findByIdEqualsAndDeletedEquals(id, 0L);
        if (oldCart != null) return mapper.map(oldCart, CartDTO.class);
        else return null;
    }

    @Override
    public Boolean updateCart(long id, CartDTO CartDTO) {
        Cart oldCart = cartRepository.findByIdEqualsAndDeletedEquals(id, 0L);
        if (oldCart == null) {
            return false;
        } else {
            cartRepository.save(mapper.map(CartDTO, Cart.class));
        }
        return true;
    }

}