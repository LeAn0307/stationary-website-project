package com.shinhands.mu.Stationary.controller.ViewController;

import com.shinhands.mu.Stationary.config.FileUploadUtil;
import com.shinhands.mu.Stationary.dto.CategoryDTO;
import com.shinhands.mu.Stationary.dto.CouponDTO;
import com.shinhands.mu.Stationary.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

        //  Add an object to prevent "Unsupport type"
        CouponDTO couponDTO = new CouponDTO();
        model.addAttribute("couponDTO", couponDTO);
        return model;
    }


    // ADD
    @PostMapping(value = "/coupon-manage-element/post")
    public String addCoupon(@ModelAttribute CouponDTO couponDTO) {
        couponService.addCoupon(couponDTO);
        return "redirect:/pages/coupon-manage-element";
    }


    //EDIT
    @PostMapping (value = "/coupon-manage-element/put/{id}")
    public String putCoupon(@ModelAttribute CouponDTO couponDTO, @PathVariable long id) {

        couponService.updateCoupon(id,couponDTO);
        return "redirect:/pages/coupon-manage-element";
    }

    @GetMapping("coupon-manage-element/delete/{id}")
    public String deleteProduct(@PathVariable long id, Model model) {
        couponService.deleteCoupon(id);
        return "redirect:/pages/coupon-manage-element";
    }


}