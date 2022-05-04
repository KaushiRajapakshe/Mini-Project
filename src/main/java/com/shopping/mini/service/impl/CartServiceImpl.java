package com.shopping.mini.service.impl;

import com.shopping.mini.model.Cart;
import com.shopping.mini.repository.CartRepository;
import com.shopping.mini.service.CartService;
import com.shopping.mini.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by @author Kaushalya Rajapakshe on 2022-03-24
 */
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    DateUtil dateUtil;

    @Override
    public Cart addCart(Cart cart) {
        cart.setUpdatedDate(dateUtil.getDateTime());
        return cartRepository.addCart(cart);
    }

    @Override
    public Cart updateCart(Cart cart) {
        cart.setUpdatedDate(dateUtil.getDateTime());
        return cartRepository.updateCart(cart);
    }

    @Override
    public Cart getCartById(int id) {
        return cartRepository.getCartById(id);
    }

    @Override
    public List<Cart> getCartAll() {
        return cartRepository.getCartAll();
    }
}
