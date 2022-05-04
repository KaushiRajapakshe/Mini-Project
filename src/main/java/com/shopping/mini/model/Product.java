package com.shopping.mini.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * Created by @author Kaushalya Rajapakshe on 2022-03-24
 */
@Getter
@Setter
public class Product {
    private int productId;
    private int categoryId;
    private String productName;
    private ProductInfo productInfo;
    private int productCount;
    private double productPrice;
    private Timestamp addedDate;
    private Timestamp updatedDate;
    private boolean active;

    public boolean getActive() {
        return this.active;
    }
}
