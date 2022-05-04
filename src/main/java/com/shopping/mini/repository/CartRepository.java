package com.shopping.mini.repository;

import com.shopping.mini.model.Cart;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by @author Kaushalya Rajapakshe on 2022-03-24
 */

@Repository
public interface CartRepository {
    Cart addCart(Cart cart);

    Cart updateCart(Cart cart);

    Cart getCartById(int id);

    List<Cart> getCartAll();
}
