package com.shinhands.mu.Stationary.controller;

import com.shinhands.mu.Stationary.dto.CouponDTO;
import com.shinhands.mu.Stationary.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController("")
@RequestMapping(value="/coupon")
public class CouponController {
    @Autowired
    CouponService CouponService;
    @RequestMapping(value="",method= RequestMethod.GET)
    public ResponseEntity getCoupon()
    {
        return ResponseEntity.ok().body(CouponService.getAllCoupons());
    }
    @PostMapping()
    public ResponseEntity<CouponDTO> addCoupon(@RequestBody CouponDTO categoryDTO)
    {
        return ResponseEntity.ok().body(CouponService.addCoupon(categoryDTO));
    }
    @GetMapping(value="/{id}")
    public ResponseEntity getCouponById(@PathVariable(name="id") long id)
    {
        return ResponseEntity.ok().body(CouponService.getCouponById(id));
    }
    @PutMapping(value="/{id}")
    public ResponseEntity updateCoupon(@PathVariable(name="id") long id,@RequestBody CouponDTO categoryDTO)
    {
        return ResponseEntity.ok().body(CouponService.updateCoupon(id,categoryDTO));
    }
    @DeleteMapping(value="/{id}")
    public ResponseEntity deleteCoupon(@PathVariable(name="id") long id)
    {
        return ResponseEntity.ok().body(CouponService.deleteCoupon(id));
    }
    @GetMapping(value="/code/{code}")
    public ResponseEntity getCouponById(@PathVariable(name="code") String code)
    {
        return ResponseEntity.ok().body(CouponService.getCartByCode(code,0l));
    }
}

