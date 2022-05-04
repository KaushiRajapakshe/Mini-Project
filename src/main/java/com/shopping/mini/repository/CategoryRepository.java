package com.shopping.mini.repository;

import com.shopping.mini.model.Category;

import java.util.List;

/**
 * Created by @author Kaushalya Rajapakshe on 2022-04-14
 */
public interface CategoryRepository {
    Category addCategory(Category category);

    Category updateCategory(Category category);

    Category getCategoryById(int id);

    List<Category> getCategoryAll();
}
