package com.shopping.mini.service;

import com.shopping.mini.model.Category;

import java.util.List;

/**
 * Created by @author Kaushalya Rajapakshe on 2022-04-07
 */
public interface CategoryService {
    Category addCategory(Category category);

    Category updateCategory(Category category);

    Category getCategoryById(int id);

    List<Category> getCategoryAll();
}
