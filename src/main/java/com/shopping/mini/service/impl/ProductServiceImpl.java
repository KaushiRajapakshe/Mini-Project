package com.shopping.mini.service.impl;

import com.shopping.mini.model.Product;
import com.shopping.mini.repository.ProductRepository;
import com.shopping.mini.service.ProductService;
import com.shopping.mini.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by @author Kaushalya Rajapakshe on 2022-04-07
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    DateUtil dateUtil;

    @Override
    public Product addProduct(Product product) {
        product.setUpdatedDate(dateUtil.getDateTime());
        return productRepository.addProduct(product);
    }

    @Override
    public Product updateProduct(Product product) {
        product.setUpdatedDate(dateUtil.getDateTime());
        return productRepository.updateProduct(product);
    }

    @Override
    public Product getProductById(int id) {
        return productRepository.getProductById(id);
    }

    @Override
    public List<Product>  getProductByName(String name) {
        return productRepository.getProductByName(name);
    }

    @Override
    public List<Product> getProductAll() {
        return productRepository.getProductAll();
    }
}
