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
    public List<CartProduct> getAllCartProducts()
    {
        return mapper.map(cartProductRepository.findAll(), new TypeToken<List<CartProductDTO>>(){}.getType());

    }
    public CartProductDTO addCartProduct(CartProductDTO cartDTO)
    {
        return mapper.map(cartProductRepository.save(mapper.map(cartDTO,CartProduct.class)), CartProductDTO.class);
    }

    public Boolean deleteCartProduct(long id)
    {
        try{
            cartProductRepository.deleteById(id);
            return true;
        }
        catch (Exception e)
        {

            System.out.println(e.getMessage());
            return false;
        }
    }
    public CartProductDTO getCartProductById(long id)
    {
        return mapper.map(cartProductRepository.findById(id).orElse(null), CartProductDTO.class);
    }
    public Boolean updateCartProduct(long id, CartProductDTO cartDTO)
    {
        CartProduct oldCartProduct=cartProductRepository.findById(id).orElse(null);
        if(oldCartProduct==null)
        {
            return false;
        }
        else
        {
            cartProductRepository.save(mapper.map(cartDTO,CartProduct.class));
        }
        return true;
    }
}