package com.shopping.mini.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * Created by @author Kaushalya Rajapakshe on 2022-04-06
 */
@Getter
@Setter
public class Order {
    private int orderId;
    private int userId;
    private Timestamp orderDate;
    private Timestamp requiredDate;
    private Timestamp shippedDate;
    private String status;
    private boolean active;

    public boolean getActive() {
        return this.active;
    }
}
