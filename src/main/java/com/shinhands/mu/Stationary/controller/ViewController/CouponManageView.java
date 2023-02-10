package com.shinhands.mu.Stationary.controller.ViewController;

import com.shinhands.mu.Stationary.dto.CategoryDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "pages")
public class CouponManageView {

    @GetMapping(value = "coupon-manage-element")
    public ModelMap CategoryElements(ModelMap model) {

//        CouponDTO[] coupon = new CouponDTO[]{new CouponDTO(1L,"1","img.url"),new CategoryDTO(2L,"1","img.url"),new CategoryDTO(3L,"1","img.url")};
//        model.addAttribute("categories",categories);
        return model;
    }
}
