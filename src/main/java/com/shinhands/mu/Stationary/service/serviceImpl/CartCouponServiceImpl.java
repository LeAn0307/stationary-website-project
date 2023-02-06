package com.shinhands.mu.Stationary.service.serviceImpl;

import com.shinhands.mu.Stationary.dto.CartCouponDTO;
import com.shinhands.mu.Stationary.entity.CartCoupon;
import com.shinhands.mu.Stationary.repository.CartCouponRepository;
import com.shinhands.mu.Stationary.service.CartCouponService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CartCouponServiceImpl implements CartCouponService {
    @Autowired
    private CartCouponRepository cartCouponRepository;
    @Autowired
    private ModelMapper mapper;
    public List<CartCoupon> getAllCartCoupons()
    {
        return mapper.map(cartCouponRepository.findAll(), new TypeToken<List<CartCouponDTO>>(){}.getType());

    }
    public CartCouponDTO addCartCoupon(CartCouponDTO cartDTO)
    {
        return mapper.map(cartCouponRepository.save(mapper.map(cartDTO,CartCoupon.class)), CartCouponDTO.class);
    }

    public Boolean deleteCartCoupon(long id)
    {
        try{
            cartCouponRepository.deleteById(id);
            return true;
        }
        catch (Exception e)
        {

            System.out.println(e.getMessage());
            return false;
        }
    }
    public CartCouponDTO getCartCouponById(long id)
    {
        return mapper.map(cartCouponRepository.findById(id).orElse(null), CartCouponDTO.class);
    }
    public Boolean updateCartCoupon(long id, CartCouponDTO cartDTO)
    {
        CartCoupon oldCartCoupon=cartCouponRepository.findById(id).orElse(null);
        if(oldCartCoupon==null)
        {
            return false;
        }
        else
        {
            cartCouponRepository.save(mapper.map(cartDTO,CartCoupon.class));
        }
        return true;
    }
}