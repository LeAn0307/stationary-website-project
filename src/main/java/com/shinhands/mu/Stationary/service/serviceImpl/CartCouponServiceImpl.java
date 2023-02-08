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
    @Override
    public List<CartCouponDTO> getAllCartCoupons() {
        return mapper.map(cartCouponRepository.findAllByDeletedEquals(0L), new TypeToken<List<CartCouponDTO>>() {
        }.getType());
    }

    @Override public
    CartCouponDTO addCartCoupon(CartCouponDTO CartCouponDTO) {
        CartCoupon CartCoupon = mapper.map(CartCouponDTO, CartCoupon.class);
        CartCoupon.setDeleted(0L);
        CartCoupon CartCoupon1 = cartCouponRepository.save(CartCoupon);
        return mapper.map(CartCoupon1, CartCouponDTO.class);
    }

    @Override
    public
    Boolean deleteCartCoupon(long id) {
        CartCoupon oldCartCoupon = cartCouponRepository.findByIdEqualsAndDeletedEquals(id, 0L);
        if (oldCartCoupon != null) {
            oldCartCoupon.setDeleted(1L);
            cartCouponRepository.save(oldCartCoupon);
            return true;
        } else return false;
    }

    @Override
    public CartCouponDTO getCartCouponById(long id) {
        CartCoupon oldCartCoupon = cartCouponRepository.findByIdEqualsAndDeletedEquals(id, 0L);
        if (oldCartCoupon != null) return mapper.map(oldCartCoupon, CartCouponDTO.class);
        else return null;
    }

    @Override
    public Boolean updateCartCoupon(long id, CartCouponDTO CartCouponDTO) {
        CartCoupon oldCartCoupon = cartCouponRepository.findByIdEqualsAndDeletedEquals(id, 0L);
        if (oldCartCoupon == null) {
            return false;
        } else {
            cartCouponRepository.save(mapper.map(CartCouponDTO, CartCoupon.class));
        }
        return true;
    }
}