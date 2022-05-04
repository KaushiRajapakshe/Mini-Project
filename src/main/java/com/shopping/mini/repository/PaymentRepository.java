package com.shopping.mini.repository;

import com.shopping.mini.model.Payment;

/**
 * Created by @author Kaushalya Rajapakshe on 2022-04-15
 */
public interface PaymentRepository {
    Payment addPayment(Payment payment);

    Payment getPaymentById(int id);
}
