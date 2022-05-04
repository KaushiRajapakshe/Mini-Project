package com.shopping.mini.repository.impl;

import com.shopping.mini.model.Order;
import com.shopping.mini.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import static com.shopping.mini.util.constants.Constant.ORDER_TABLE;


/**
 * Created by @author Kaushalya Rajapakshe on 2022-04-15
 */
@Service
public class OrderRepositoryImpl implements OrderRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcOperations ops;

    @Override
    public Order addOrder(Order order) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO "+ ORDER_TABLE + " ( `user_id`, `order_date`, `required_date`, `status`, `active`) " +
                "VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, order.getUserId());
            ps.setTimestamp(2, order.getOrderDate());
            ps.setTimestamp(3, order.getRequiredDate());
            ps.setString(4, order.getStatus());
            ps.setBoolean(5, order.getActive());
            return ps;
        }, keyHolder);
        order.setOrderId(keyHolder.getKey().intValue());
        return order;
    }

    @Override
    public Order updateOrder(Order order) {
        String sql = "UPDATE " + ORDER_TABLE + " SET shipped_date = '" + order.getShippedDate() + "', " +
                " status = '" + order.getStatus() + "' " +
                " WHERE order_id = " + order.getOrderId() + "";
        int result = jdbcTemplate.update(sql);
        if(result > 0)
            return order;
        else
            return new Order();
    }

    @Override
    public Order getOrderById(int id) {
        String sql = "SELECT * FROM " + ORDER_TABLE + " WHERE order_id = "
                + id + "";
        List<Order> order = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Order.class));
        if (order.isEmpty())
            return new Order();
        else
            return order.get(0);
    }

    @Override
    public List<Order> getOrderByUserId(int userId) {
        String sql = "SELECT * FROM " + ORDER_TABLE + " WHERE user_id = "
                + userId + "";
        List<Order> orders = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Order.class));
        return orders;
    }

    @Override
    public List<Order> getOrderAll() {
        String sql = "SELECT * FROM " + ORDER_TABLE;
        List<Order> orders = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Order.class));
        return orders;
    }
}
