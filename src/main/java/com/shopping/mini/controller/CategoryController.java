package com.shopping.mini.controller;

import com.shopping.mini.model.Category;
import com.shopping.mini.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by @author Kaushalya Rajapakshe on 2022-04-07
 */
@RestController
@RequestMapping("/category")
@CrossOrigin()
public class CategoryController {
    @Autowired
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<Object> addCategory(@RequestBody Category category) {
        Category categoryObj = categoryService.addCategory(category);
        try {
            if (categoryObj.getCategoryName() != null)
                return new ResponseEntity<>("Category successfully added", HttpStatus.CREATED);
            else
                return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<Object> updateCategory(@RequestBody Category category) {
        try {
            Category categoryObj = categoryService.updateCategory(category);
            if (categoryObj.getCategoryName() != null)
                return new ResponseEntity<>("Category successfully updated", HttpStatus.OK);
            else
                return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCategoryById(@PathVariable int id) {
        try {
            return new ResponseEntity<>(categoryService.getCategoryById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getCategoryAll() {
        try {
            return new ResponseEntity<>(categoryService.getCategoryAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
