package com.shopping.mini.mapper;

import com.shopping.mini.model.CardProduct;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by @author Kaushalya Rajapakshe on 2022-04-11
 */
public class CardProductMapper implements RowMapper<CardProduct> {
    @Override
    public CardProduct mapRow(ResultSet rs, int rowNum) throws SQLException {
        CardProduct cardProduct = new CardProduct();

        cardProduct.setProductId(rs.getInt("product_id"));
        cardProduct.setCartId(rs.getInt("cart_id"));
        cardProduct.setProductCount(rs.getInt("product_count"));
        cardProduct.setProductTotalPrice(rs.getDouble("product_total_price"));
        cardProduct.setActive(rs.getBoolean("active"));
        return cardProduct;
    }
}
