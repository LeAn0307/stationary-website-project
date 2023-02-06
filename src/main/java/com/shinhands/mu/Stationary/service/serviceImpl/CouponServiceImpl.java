package com.shinhands.mu.Stationary.service.serviceImpl;

import com.shinhands.mu.Stationary.dto.CouponDTO;
import com.shinhands.mu.Stationary.entity.Coupon;
import com.shinhands.mu.Stationary.repository.CouponRepository;
import com.shinhands.mu.Stationary.service.CouponService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CouponServiceImpl implements CouponService {
    @Autowired
    private CouponRepository couponRepository;
    @Autowired
    private ModelMapper mapper;
    public List<Coupon> getAllCoupons()
    {
        return mapper.map(couponRepository.findAll(), new TypeToken<List<CouponDTO>>(){}.getType());

    }
    public CouponDTO addCoupon(CouponDTO couponDTO)
    {
        return mapper.map(couponRepository.save(mapper.map(couponDTO,Coupon.class)), CouponDTO.class);
    }

    public Boolean deleteCoupon(long id)
    {
        try{
            couponRepository.deleteById(id);
            return true;
        }
        catch (Exception e)
        {

            System.out.println(e.getMessage());
            return false;
        }
    }
    public CouponDTO getCouponById(long id)
    {
        return mapper.map(couponRepository.findById(id).orElse(null), CouponDTO.class);
    }
    public Boolean updateCoupon(long id, CouponDTO couponDTO)
    {
        Coupon oldCoupon=couponRepository.findById(id).orElse(null);
        if(oldCoupon==null)
        {
            return false;
        }
        else
        {
            couponRepository.save(mapper.map(couponDTO,Coupon.class));
        }
        return true;
    }
}