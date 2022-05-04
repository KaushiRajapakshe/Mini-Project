package com.shopping.mini.service.impl;

import com.shopping.mini.model.Payment;
import com.shopping.mini.repository.PaymentRepository;
import com.shopping.mini.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by @author Kaushalya Rajapakshe on 2022-04-07
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    @Override
    public Payment addPayment(Payment payment) {
        return paymentRepository.addPayment(payment);
    }

    @Override
    public Payment getPaymentById(int id) {
        return paymentRepository.getPaymentById(id);
    }
}
