package com.shopping.mini.service;

import com.shopping.mini.model.Cart;

import java.util.List;

/**
 * Created by @author Kaushalya Rajapakshe on 2022-03-24
 */
public interface CartService {
    Cart addCart(Cart cart);

    Cart updateCart(Cart cart);

    Cart getCartById(int id);

    List<Cart> getCartAll();
}
