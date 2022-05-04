package com.shopping.mini.service.impl;


import com.shopping.mini.model.Category;
import com.shopping.mini.repository.CategoryRepository;
import com.shopping.mini.service.CategoryService;
import com.shopping.mini.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by @author Kaushalya Rajapakshe on 2022-04-07
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    DateUtil dateUtil;

    @Override
    public Category addCategory(Category category) {
        category.setUpdatedDate(dateUtil.getDateTime());
        return categoryRepository.addCategory(category);
    }

    @Override
    public Category updateCategory(Category category) {
        category.setUpdatedDate(dateUtil.getDateTime());
        return categoryRepository.updateCategory(category);
    }

    @Override
    public Category getCategoryById(int id) {
        return categoryRepository.getCategoryById(id);
    }

    @Override
    public List<Category> getCategoryAll() {
        return categoryRepository.getCategoryAll();
    }
}
