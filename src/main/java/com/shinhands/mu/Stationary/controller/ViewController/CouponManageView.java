package com.shinhands.mu.Stationary.controller.ViewController;

import com.shinhands.mu.Stationary.dto.CategoryDTO;
import com.shinhands.mu.Stationary.dto.CouponDTO;
import com.shinhands.mu.Stationary.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "pages")
public class CouponManageView {

    @Autowired
    CouponService couponService;
    @GetMapping(value = "coupon-manage-element")
    public ModelMap CategoryElements(ModelMap model) {
        List<CouponDTO> coupon =  couponService.getAllCoupons();
        model.addAttribute("coupon",coupon);
        return model;
    }
}
