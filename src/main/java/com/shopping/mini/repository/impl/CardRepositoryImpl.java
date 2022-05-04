package com.shopping.mini.repository.impl;

import com.shopping.mini.mapper.CardProductMapper;
import com.shopping.mini.model.CardProduct;
import com.shopping.mini.model.Cart;
import com.shopping.mini.repository.CartRepository;
import com.shopping.mini.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import static com.shopping.mini.util.constants.Constant.CART_PRODUCT_TABLE;
import static com.shopping.mini.util.constants.Constant.CART_TABLE;

/**
 * Created by @author Kaushalya Rajapakshe on 2022-03-24
 */
@Service
public class CardRepositoryImpl implements CartRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcOperations ops;

    @Autowired
    DateUtil dateUtil;

    @Override
    public Cart addCart(Cart cart) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO "+ CART_TABLE + " ( `cart_name`, `active`, `updated_date` )" +
                " VALUES (?, ?, ?)";
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, cart.getCartName());
            ps.setBoolean(2, cart.getActive());
            ps.setTimestamp(3, cart.getUpdatedDate());
            return ps;
        }, keyHolder);
        cart.setCartId(keyHolder.getKey().intValue());
        return cart;
    }

    @Override
    public Cart updateCart(Cart cart) {
        String sql = "UPDATE " + CART_TABLE + " SET cart_name = '" + cart.getCartName() + "', " +
                " active = " + cart.getActive() + ", " +
                " updated_date = '" + cart.getUpdatedDate() + "' " +
                " WHERE cart_id = " +cart.getCartId() + "";
        int result = jdbcTemplate.update(sql);
        List<CardProduct> cardProducts = cart.getCardProduct();
        if(cardProducts.iterator().hasNext()){
            CardProduct cardProduct = cardProducts.iterator().next();
            String getCartProductSql = "SELECT * FROM " + CART_PRODUCT_TABLE + " WHERE cart_id = "
                    + cart.getCartId() + " AND product_id = " + cardProduct.getProductId() + "";
            List<CardProduct> cardProductExist = jdbcTemplate.query(getCartProductSql, new BeanPropertyRowMapper<>(CardProduct.class));
            if(cardProductExist.isEmpty()) {
                String cartProductInsertSql = "INSERT INTO "+ CART_PRODUCT_TABLE + " ( `cart_id`, `product_id`, " +
                        "`product_count`, `product_total_price`, `active` ) VALUES (?, ?, ?, ?, ?)";
                result = jdbcTemplate.update(con -> {
                    PreparedStatement ps = con.prepareStatement(cartProductInsertSql, Statement.RETURN_GENERATED_KEYS);
                    ps.setInt(1, cart.getCartId());
                    ps.setInt(2, cardProduct.getProductId());
                    ps.setInt(3, cardProduct.getProductCount());
                    ps.setDouble(4, cardProduct.getProductTotalPrice());
                    ps.setBoolean(5, cardProduct.getActive());
                    return ps;
                });
            } else if (cardProductExist.get(0).getProductId() >= 1 && cardProductExist.get(0).getCartId() >= 1) {
                String cartProductUpdateSql = "UPDATE " + CART_PRODUCT_TABLE +
                        " SET product_count = " + cardProduct.getProductCount() + ", " +
                        " product_total_price = " + cardProduct.getProductTotalPrice() + ", " +
                        " active = " + cardProduct.getActive() + " " +
                        " WHERE cart_id = " + cart.getCartId() +
                        " AND product_id = " + cardProduct.getProductId() + "";
                result = jdbcTemplate.update(cartProductUpdateSql);
            }
        }
        if(result > 0)
            return cart;
        else
            new Cart();
        return cart;
    }

    @Override
    public Cart getCartById(int id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("cart_id", id);
        List<CardProduct> cart = ops.query("" + "SELECT * FROM cart c, cart_product p WHERE c.cart_id = :cart_id " +
                        "AND p.cart_id = :cart_id", namedParameters, new CardProductMapper());

        String sql = "SELECT * FROM " + CART_TABLE + " WHERE cart_id = " + id + "";
        List<Cart> cartList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Cart.class));
        if (cartList.isEmpty())
            return new Cart();
        else {
            cartList.get(0).setCardProduct(cart);
            return cartList.get(0);
        }
    }

    @Override
    public List<Cart> getCartAll() {
        String sql = "SELECT * FROM " + CART_TABLE +"";
        List<Cart> carts = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Cart.class));
        return carts;
    }
}
