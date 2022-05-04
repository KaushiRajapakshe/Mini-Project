package com.shopping.mini.repository.impl;

import com.shopping.mini.model.Payment;
import com.shopping.mini.repository.PaymentRepository;
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

import static com.shopping.mini.util.constants.Constant.PAYMENT_TABLE;

/**
 * Created by @author Kaushalya Rajapakshe on 2022-04-15
 */
@Service
public class PaymentRepositoryImpl implements PaymentRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcOperations ops;
    @Override
    public Payment addPayment(Payment payment) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO "+ PAYMENT_TABLE + " ( `user_id`, `amount`, `payment_date`) " +
                "VALUES (?, ?, ?)";
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, payment.getUserId());
            ps.setDouble(2, payment.getAmount());
            ps.setTimestamp(3, payment.getPaymentDate());
            return ps;
        }, keyHolder);
        payment.setPaymentId(keyHolder.getKey().intValue());
        return payment;
    }

    @Override
    public Payment getPaymentById(int id) {
        String sql = "SELECT * FROM " + PAYMENT_TABLE + " WHERE payment_id = "
                + id + "";
        List<Payment> payments = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Payment.class));
        if (payments.isEmpty())
            return new Payment();
        else
            return payments.get(0);
    }
}
