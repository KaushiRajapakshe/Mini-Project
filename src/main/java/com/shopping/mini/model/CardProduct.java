package com.shopping.mini.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by @author Kaushalya Rajapakshe on 2022-03-24
 */
@Getter
@Setter
public class CardProduct {
    private int cartId;
    private int productId;
    private int productCount;
    private double productTotalPrice;
    private boolean active;

    public boolean getActive() {
        return this.active;
    }
}
