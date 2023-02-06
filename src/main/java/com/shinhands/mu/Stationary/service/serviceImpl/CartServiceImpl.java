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
    public List<Cart> getAllCarts()
    {
        return mapper.map(cartRepository.findAll(), new TypeToken<List<CartDTO>>(){}.getType());

    }
    public CartDTO addCart(CartDTO cartDTO)
    {
        return mapper.map(cartRepository.save(mapper.map(cartDTO,Cart.class)), CartDTO.class);
    }

    public Boolean deleteCart(long id)
    {
        try{
            cartRepository.deleteById(id);
            return true;
        }
        catch (Exception e)
        {

            System.out.println(e.getMessage());
            return false;
        }
    }
    public CartDTO getCartById(long id)
    {
        return mapper.map(cartRepository.findById(id).orElse(null), CartDTO.class);
    }
    public Boolean updateCart(long id, CartDTO cartDTO)
    {
        Cart oldCart=cartRepository.findById(id).orElse(null);
        if(oldCart==null)
        {
            return false;
        }
        else
        {
            cartRepository.save(mapper.map(cartDTO,Cart.class));
        }
        return true;
    }
}