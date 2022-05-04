package com.shopping.mini.controller;

import com.shopping.mini.model.Cart;
import com.shopping.mini.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by @author Kaushalya Rajapakshe on 2022-03-24
 */
@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<Object> addCart(@RequestBody Cart cart) {
        try {
            return new ResponseEntity<>(cartService.addCart(cart), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<Object> updateCart(@RequestBody Cart cart) {
        try {
            return new ResponseEntity<>(cartService.updateCart(cart), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCartById(@PathVariable int id) {
        try {
            return new ResponseEntity<>(cartService.getCartById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getCartAll() {
        try {
            return new ResponseEntity<>(cartService.getCartAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
