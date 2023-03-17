package com.shinhands.mu.Stationary.controller;

import com.shinhands.mu.Stationary.dto.CartProductDTO;
import com.shinhands.mu.Stationary.service.CartProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController("")
@RequestMapping(value="/api/cartproduct")
public class CartProductController {
    @Autowired
    CartProductService cartProductService;
    @RequestMapping(value="",method= RequestMethod.GET)
    public ResponseEntity getCartProduct()
    {
        return ResponseEntity.ok().body(cartProductService.getAllCartProducts());
    }
    @PostMapping("")
    public ResponseEntity<Boolean> addCartProduct(@RequestBody CartProductDTO categoryDTO)
    {
        System.out.println(1234);
        return ResponseEntity.ok().body(cartProductService.addCartProduct(categoryDTO));

    }
    @GetMapping(value="/{id}")
    public ResponseEntity getCartProductById(@PathVariable(name="id") long id)
    {
        return ResponseEntity.ok().body(cartProductService.getCartProductById(id));
    }
    @PutMapping(value="/{id}")
    public ResponseEntity updateCartProduct(@PathVariable(name="id") long id,@RequestBody CartProductDTO categoryDTO)
    {
        return ResponseEntity.ok().body(cartProductService.updateCartProduct(id,categoryDTO));
    }
    @DeleteMapping(value="/{id}")
    public ResponseEntity deleteCartProduct(@PathVariable(name="id") long id)
    {
        return ResponseEntity.ok().body(cartProductService.deleteCartProduct(id));
    }
    @GetMapping("/counterproductive/{id}")
    public ResponseEntity<Long> CountCartProduct(@PathVariable(name="id") long id)
    {
        return ResponseEntity.ok().body(cartProductService.countProductIdInCart(id));
    }
}
