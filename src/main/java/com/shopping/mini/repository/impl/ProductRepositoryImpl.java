package com.shopping.mini.repository.impl;

import com.shopping.mini.model.Category;
import com.shopping.mini.model.Product;
import com.shopping.mini.repository.CategoryRepository;
import com.shopping.mini.repository.ProductRepository;
import com.shopping.mini.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static com.shopping.mini.util.constants.Constant.PRODUCT_TABLE;

/**
 * Created by @author Kaushalya Rajapakshe on 2022-04-15
 */
@Service
public class ProductRepositoryImpl implements ProductRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcOperations ops;

    @Autowired
    DateUtil dateUtil;

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Product addProduct(Product product) {
        // check category id exist
        Category categoryById = categoryRepository.getCategoryById(product.getCategoryId());
        // check product name duplicated
        String sqlCheck = "SELECT * FROM  " + PRODUCT_TABLE + " WHERE product_name = '"
                + product.getProductName() + "'";
        List<Product> productsList = jdbcTemplate.query(sqlCheck, new BeanPropertyRowMapper<>(Product.class));
        if (categoryById.getCategoryName() != null && productsList.isEmpty()) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            String sql = "INSERT INTO " + PRODUCT_TABLE + " ( `category_id`, `product_name`, `product_info_detail`," +
                    "`product_info_count`, `product_count`, " +
                    "`product_price`, `active`, `updated_date` ) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(con -> {
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, product.getCategoryId());
                ps.setString(2, product.getProductName());
                ps.setString(3, product.getProductInfo().getDetail());
                ps.setDouble(4, product.getProductInfo().getAmount());
                ps.setInt(5, product.getProductCount());
                ps.setDouble(6, product.getProductPrice());
                ps.setBoolean(7, product.getActive());
                ps.setTimestamp(8, product.getUpdatedDate());
                return ps;
            }, keyHolder);
            product.setProductId(keyHolder.getKey().intValue());
            return product;
        } else
            return new Product();
    }

    @Override
    public Product updateProduct(Product product) {
        String sqlCheck = "SELECT * FROM  " + PRODUCT_TABLE + " WHERE product_name = '"
                + product.getProductName() + "'";
        List<Product> productsList = jdbcTemplate.query(sqlCheck, new BeanPropertyRowMapper<>(Product.class));
        String sql = "UPDATE " + PRODUCT_TABLE + " SET category_id = " + product.getCategoryId() + ", " +
                " product_name = '" + product.getProductName() + "', " +
                " product_info_detail = '" + product.getProductInfo().getDetail() + "', " +
                " product_info_count = '" + product.getProductInfo().getAmount() + "', " +
                " product_count = '" + product.getProductCount() + "', " +
                " product_price = '" + product.getProductPrice() + "', " +
                " active = " + product.getActive() + ", " +
                " updated_date = '" + product.getUpdatedDate() + "' " +
                " WHERE product_id = " + product.getProductId() + "";

        if (productsList.isEmpty()) {
            jdbcTemplate.update(sql);
            return product;
        } else if (productsList.get(0).getProductId() == product.getProductId()) {
            jdbcTemplate.update(sql);
            return product;
        } else {
            return new Product();
        }
    }

    @Override
    public Product getProductById(int id) {
        String sql = "SELECT * FROM " + PRODUCT_TABLE + " WHERE product_id = "
                + id + "";
        List<Product> product = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Product.class));
        if (product.isEmpty())
            return new Product();
        else
            return product.get(0);
    }

    @Override
    public List<Product> getProductByName(String name) {
        String sql = "SELECT * FROM " + PRODUCT_TABLE + " WHERE category_id = (SELECT category_id FROM " +
                PRODUCT_TABLE + " WHERE product_name = '" + name + "');";
        List<Product> product = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Product.class));
        if (product.isEmpty())
            return Collections.emptyList();
        else
            return product;
    }

    @Override
    public List<Product> getProductAll() {
        String sql = "SELECT * FROM " + PRODUCT_TABLE;
        List<Product> products = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Product.class));
        return products;
    }

}
