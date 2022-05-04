package com.shopping.mini.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * Created by @author Kaushalya Rajapakshe on 2022-04-06
 */
@Getter
@Setter
public class Payment {
    private int paymentId;
    private int userId;
    private double amount;
    private Timestamp paymentDate;
}
