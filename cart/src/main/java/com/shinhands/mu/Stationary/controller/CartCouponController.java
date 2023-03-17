package com.shinhands.mu.Stationary.controller;

import com.shinhands.mu.Stationary.dto.CartCouponDTO;
import com.shinhands.mu.Stationary.service.CartCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController("")
@RequestMapping(value="/api/cartcoupon")
public class CartCouponController {
    @Autowired
    CartCouponService cartCouponService;
    @RequestMapping(value="",method= RequestMethod.GET)
    public ResponseEntity getCartCoupon()
    {
        return ResponseEntity.ok().body(cartCouponService.getAllCartCoupons());
    }
    @PostMapping()
    public ResponseEntity<CartCouponDTO> addCartCoupon(@RequestBody CartCouponDTO categoryDTO)
    {
        return ResponseEntity.ok().body(cartCouponService.addCartCoupon(categoryDTO));
    }
    @GetMapping(value="/{id}")
    public ResponseEntity getCartCouponById(@PathVariable(name="id") long id)
    {
        return ResponseEntity.ok().body(cartCouponService.getCartCouponById(id));
    }
    @PutMapping(value="/{id}")
    public ResponseEntity updateCartCoupon(@PathVariable(name="id") long id,@RequestBody CartCouponDTO categoryDTO)
    {
        return ResponseEntity.ok().body(cartCouponService.updateCartCoupon(id,categoryDTO));
    }
    @DeleteMapping(value="/{id}")
    public ResponseEntity deleteCartCoupon(@PathVariable(name="id") long id)
    {
        return ResponseEntity.ok().body(cartCouponService.deleteCartCoupon(id));
    }
}
