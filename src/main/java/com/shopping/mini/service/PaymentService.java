package com.shopping.mini.service;

import com.shopping.mini.model.Payment;

/**
 * Created by @author Kaushalya Rajapakshe on 2022-04-07
 */
public interface PaymentService {
    Payment addPayment(Payment payment);

    Payment getPaymentById(int id);
}
