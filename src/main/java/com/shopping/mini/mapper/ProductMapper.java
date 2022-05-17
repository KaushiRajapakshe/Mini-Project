package com.shopping.mini.mapper;

import com.shopping.mini.model.Product;
import com.shopping.mini.model.ProductInfo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by @author Kaushalya Rajapakshe on 2022-05-01
 */
public class ProductMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();

        product.setProductId(rs.getInt("product_id"));
        product.setCategoryId(rs.getInt("category_id"));
        product.setProductName(rs.getString("product_name"));
        ProductInfo productInfo = new ProductInfo();
        productInfo.setDetail(rs.getString("product_info_detail"));
        productInfo.setAmount(rs.getInt("product_info_count"));
        product.setProductInfo(productInfo);
        product.setProductCount(rs.getInt("product_count"));
        product.setProductPrice(rs.getDouble("product_price"));
        product.setAddedDate(rs.getTimestamp("added_date"));
        product.setUpdatedDate(rs.getTimestamp("updated_date"));
        product.setImgURL(rs.getString("imgURL"));
        product.setActive(rs.getBoolean("active"));
        return product;
    }
}

