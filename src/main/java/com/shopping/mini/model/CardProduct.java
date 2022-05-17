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
    private String productName;
    private int productCount;
    private String productInfo;
    private double productTotalPrice;
    private double productPrice;
    private boolean active;
    private String imgURL;
    private double availableCount;

    public boolean getActive() {
        return this.active;
    }
}
