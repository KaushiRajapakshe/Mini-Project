package com.shopping.mini.repository.impl;


import com.shopping.mini.model.Category;
import com.shopping.mini.repository.CategoryRepository;
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
import java.util.List;

import static com.shopping.mini.util.constants.Constant.CATEGORY_TABLE;

/**
 * Created by @author Kaushalya Rajapakshe on 2022-04-14
 */
@Service
public class CategoryRepositoryImpl implements CategoryRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcOperations ops;

    @Autowired
    DateUtil dateUtil;

    @Override
    public Category addCategory(Category category) {
        String sqlCheck = "SELECT * FROM  " + CATEGORY_TABLE + " WHERE category_name = '"
                + category.getCategoryName() + "'";
        List<Category> categoryList = jdbcTemplate.query(sqlCheck, new BeanPropertyRowMapper<>(Category.class));
        if (categoryList.isEmpty()) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            String sql = "INSERT INTO " + CATEGORY_TABLE + " ( `category_name`, `active`, `updated_date`, `imgURL`)" +
                    " VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(con -> {
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, category.getCategoryName());
                ps.setBoolean(2, category.getActive());
                ps.setTimestamp(3, category.getUpdatedDate());
                ps.setString(4, category.getImgURL());
                return ps;
            }, keyHolder);
            category.setCategoryId(keyHolder.getKey().intValue());
            return category;
        } else {
            return new Category();
        }
    }

    @Override
    public Category updateCategory(Category category) {
        String sqlCheck = "SELECT * FROM  " + CATEGORY_TABLE + " WHERE category_name = '"
                + category.getCategoryName() + "'";
        List<Category> categoryList = jdbcTemplate.query(sqlCheck, new BeanPropertyRowMapper<>(Category.class));
        String sql = "UPDATE " + CATEGORY_TABLE + " SET category_name = '" + category.getCategoryName() + "', " +
                " active = " + category.getActive() + ", " +
                " updated_date = '" + category.getUpdatedDate() + "' " +
                " imgURL = '" + category.getImgURL() + "'" +
                " WHERE category_id = " + category.getCategoryId() + "";
        if(categoryList.isEmpty()) {
            jdbcTemplate.update(sql);
            return category;
        } else if (categoryList.get(0).getCategoryId() == category.getCategoryId()) {
            jdbcTemplate.update(sql);
            return category;
        } else {
            return new Category();
        }
    }


    @Override
    public Category getCategoryById(int id) {
        String sql = "SELECT * FROM " + CATEGORY_TABLE + " WHERE category_id = "
                + id + "";
        List<Category> category = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Category.class));
        if (category.isEmpty())
            return new Category();
        else
            return category.get(0);
    }

    @Override
    public List<Category> getCategoryAll() {
        String sql = "SELECT * FROM " + CATEGORY_TABLE;
        List<Category> categories = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Category.class));
        return categories;
    }
}
