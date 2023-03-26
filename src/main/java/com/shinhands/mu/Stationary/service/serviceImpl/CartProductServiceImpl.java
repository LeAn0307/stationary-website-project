package com.shinhands.mu.Stationary.service.serviceImpl;

import com.shinhands.mu.Stationary.dto.CartProductDTO;
import com.shinhands.mu.Stationary.entity.CartProduct;
import com.shinhands.mu.Stationary.repository.CartProductRepository;
import com.shinhands.mu.Stationary.service.CartProductService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CartProductServiceImpl implements CartProductService {
    @Autowired
    private CartProductRepository cartProductRepository;
    @Autowired
    private ModelMapper mapper;
    @Override
    public List<CartProductDTO> getAllCartProducts() {
        return mapper.map(cartProductRepository.findAllByDeletedEquals(0L), new TypeToken<List<CartProductDTO>>() {
        }.getType());
    }

    @Override public
    CartProductDTO addCartProduct(CartProductDTO CartProductDTO) {
        CartProduct CartProduct = mapper.map(CartProductDTO, CartProduct.class);
        CartProduct.setDeleted(0L);
        CartProduct CartProduct1 = cartProductRepository.save(CartProduct);
        return mapper.map(CartProduct1, CartProductDTO.class);
    }

    @Override
    public
    Boolean deleteCartProduct(long id) {
        CartProduct oldCartProduct = cartProductRepository.findByIdEqualsAndDeletedEquals(id, 0L);
        if (oldCartProduct != null) {
            oldCartProduct.setDeleted(1L);
            cartProductRepository.save(oldCartProduct);
            return true;
        } else return false;
    }

    @Override
    public CartProductDTO getCartProductById(long id) {
        CartProduct oldCartProduct = cartProductRepository.findByIdEqualsAndDeletedEquals(id, 0L);
        if (oldCartProduct != null) return mapper.map(oldCartProduct, CartProductDTO.class);
        else return null;
    }

    @Override
    public Boolean updateCartProduct(long id, CartProductDTO CartProductDTO) {
        CartProduct oldCartProduct = cartProductRepository.findByIdEqualsAndDeletedEquals(id, 0L);
        if (oldCartProduct == null) {
            return false;
        } else {
            cartProductRepository.save(mapper.map(CartProductDTO, CartProduct.class));
        }
        return true;
    }
}