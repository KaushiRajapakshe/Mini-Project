package com.shopping.mini.service.impl;

import com.shopping.mini.model.Order;
import com.shopping.mini.repository.OrderRepository;
import com.shopping.mini.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by @author Kaushalya Rajapakshe on 2022-04-07
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public Order addOrder(Order order) {
        return orderRepository.addOrder(order);
    }

    @Override
    public Order updateOrder(Order order) {
        return orderRepository.updateOrder(order);
    }

    @Override
    public Order getOrderById(int id) {
        return orderRepository.getOrderById(id);
    }

    @Override
    public List<Order> getOrderByUserId(int userId) {
        return orderRepository.getOrderByUserId(userId);
    }

    @Override
    public List<Order> getOrderAll() {
        return orderRepository.getOrderAll();
    }
}
