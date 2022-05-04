package com.shopping.mini.repository;

import com.shopping.mini.model.Product;

import java.util.List;

/**
 * Created by @author Kaushalya Rajapakshe on 2022-04-15
 */
public interface ProductRepository {
    Product addProduct(Product product);

    Product updateProduct(Product product);

    Product getProductById(int id);

    List<Product>  getProductByName(String name);

    List<Product> getProductAll();
}
