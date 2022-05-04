package com.shopping.mini.service;

import com.shopping.mini.model.Order;

import java.util.List;

/**
 * Created by @author Kaushalya Rajapakshe on 2022-04-07
 */
public interface OrderService {
    Order addOrder(Order order);

    Order updateOrder(Order order);

    Order getOrderById(int id);

    List<Order> getOrderByUserId(int userId);

    List<Order> getOrderAll();
}
