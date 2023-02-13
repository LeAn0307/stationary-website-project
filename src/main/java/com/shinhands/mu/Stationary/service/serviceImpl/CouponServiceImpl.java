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
    @Override
    public List<CouponDTO> getAllCoupons() {
        return mapper.map(couponRepository.findAllByDeletedEquals(0L), new TypeToken<List<CouponDTO>>() {
        }.getType());
    }

    @Override public
    CouponDTO addCoupon(CouponDTO CouponDTO) {
        Coupon Coupon = mapper.map(CouponDTO, Coupon.class);
        Coupon.setDeleted(0L);
        Coupon Coupon1 = couponRepository.save(Coupon);
        return mapper.map(Coupon1, CouponDTO.class);
    }

    @Override
    public
    Boolean deleteCoupon(long id) {
        Coupon oldCoupon = couponRepository.findByIdEqualsAndDeletedEquals(id, 0L);
        if (oldCoupon != null) {
            oldCoupon.setDeleted(1L);
            couponRepository.save(oldCoupon);
            return true;
        } else return false;
    }

    @Override
    public CouponDTO getCouponById(long id) {
        Coupon oldCoupon = couponRepository.findByIdEqualsAndDeletedEquals(id, 0L);
        if (oldCoupon != null) return mapper.map(oldCoupon, CouponDTO.class);
        else return null;
    }

    @Override
    public Boolean updateCoupon(long id, CouponDTO CouponDTO) {
        Coupon oldCoupon = couponRepository.findByIdEqualsAndDeletedEquals(id, 0L);
        if (oldCoupon == null) {
            return false;
        } else {
            couponRepository.save(mapper.map(CouponDTO, Coupon.class));
        }
        return true;
    }

    @Override
    public CouponDTO getCartByCode(String code, long deleted) {
        Coupon coupon = couponRepository.findByCodeEqualsAndDeletedEquals(code,deleted);
        if (coupon != null) return mapper.map(coupon, CouponDTO.class);
        else return null;

    }
}