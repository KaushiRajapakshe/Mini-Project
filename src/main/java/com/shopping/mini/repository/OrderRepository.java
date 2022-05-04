package com.shopping.mini.repository;

import com.shopping.mini.model.Order;

import java.util.List;

/**
 * Created by @author Kaushalya Rajapakshe on 2022-04-15
 */
public interface OrderRepository {
    Order addOrder(Order order);

    Order updateOrder(Order order);

    Order getOrderById(int id);

    List<Order> getOrderByUserId(int userId);

    List<Order> getOrderAll();
}
