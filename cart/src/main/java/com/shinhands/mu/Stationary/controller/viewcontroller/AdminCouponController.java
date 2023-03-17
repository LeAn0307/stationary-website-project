package com.shinhands.mu.Stationary.controller.viewcontroller;

import com.shinhands.mu.Stationary.dto.CouponDTO;
import com.shinhands.mu.Stationary.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "admin")
public class AdminCouponController {

    @Autowired
    CouponService couponService;
    private ObjectError objectError;

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
        System.out.println(couponDTO);
        couponService.addCoupon(couponDTO);
        return "redirect:/admin/coupon";
    }

    //EDIT
    @PostMapping (value = "/coupon/update")
    public String putCoupon(@ModelAttribute @Valid CouponDTO couponDTO, BindingResult result , Model model, Errors err) {

        model.addAttribute("coupon",couponDTO);

        String fieldName = "";
        if (result.hasErrors() ) {
            for (int i = 0; i < result.getAllErrors().size(); i++) {

                ObjectError objectError = result.getAllErrors().get(i);
                FieldError fieldError = (FieldError) objectError;

                fieldName = fieldError.getField();

                model.addAttribute(fieldName,objectError.getDefaultMessage());
            }
            return "admin/edit-coupon";
        }
        couponService.updateCoupon(couponDTO.getId(),couponDTO);

        return "redirect:/admin/coupon";
    }
    //GET COUPON BY ID - TO EDIT
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