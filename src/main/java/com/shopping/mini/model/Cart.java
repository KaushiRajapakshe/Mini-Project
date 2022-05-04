package com.shopping.mini.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by @author Kaushalya Rajapakshe on 2022-03-24
 */
@Getter
@Setter
public class Cart {
    private int cartId;
    private String cartName;
    private List<CardProduct> cardProduct;
    private Timestamp addedDate;
    private Timestamp updatedDate;
    private boolean active;

    public boolean getActive() {
        return this.active;
    }
}
