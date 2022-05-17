package com.shopping.mini.controller;

import com.shopping.mini.model.Payment;
import com.shopping.mini.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by @author Kaushalya Rajapakshe on 2022-04-07
 */
@RestController
@RequestMapping("/payment")
@CrossOrigin()
public class PaymentController {
    @Autowired
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<Object> addPayment(@RequestBody Payment payment) {
        try {
            return new ResponseEntity<>(paymentService.addPayment(payment), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPaymentById(@PathVariable int id) {
        try {
            return new ResponseEntity<>(paymentService.getPaymentById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
