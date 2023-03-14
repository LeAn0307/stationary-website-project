package com.shinhands.mu.Stationary.controller.ViewController;

import com.shinhands.mu.Stationary.dto.CouponDTO;
import com.shinhands.mu.Stationary.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = "admin")
public class CouponManageView {

    @Autowired
    CouponService couponService;


    @GetMapping(value = "coupon")
    public ModelMap CategoryElements(ModelMap model) {
        List<CouponDTO> coupon =  couponService.getAllCoupons();
        model.addAttribute("coupon", coupon);

        // Add an object to prevent "Unsupport type"
        CouponDTO couponDTO = new CouponDTO();
        model.addAttribute("couponDTO", couponDTO);
        return model;
    }


    // ADD
    @PostMapping(value = "/coupon/post")
    public String addCoupon(@ModelAttribute CouponDTO couponDTO, Model model) {
        couponService.addCoupon(couponDTO);
        return "redirect:/admin/coupon";
    }

    //EDIT
    @PostMapping (value = "/coupon/update")
    public String putCoupon(@ModelAttribute CouponDTO couponDTO, Model model) {
        couponService.updateCoupon(couponDTO.getId(),couponDTO);
        return "redirect:/admin/coupon";
    }
    //    GET COUPON BY ID  - TO EDIT
    @GetMapping(value = "coupon/{id}")
    public String getCategoryById(@PathVariable(name="id") long id,Model model) {
        CouponDTO couponDTO=couponService.getCouponById(id);
        model.addAttribute("coupon",couponDTO);
        return "admin/edit-coupon";
    }

    //DELETE
    @GetMapping("coupon/delete/{id}")
    public String deleteProduct(@PathVariable long id, Model model) {
        couponService.deleteCoupon(id);
        return "redirect:/admin/coupon";
    }


}