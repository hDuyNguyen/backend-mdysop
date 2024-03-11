package com.project.mdyshop.controller;

import com.project.mdyshop.dto.request.CreateCategoryRequest;
import com.project.mdyshop.dto.request.SubCategoryRequest;
import com.project.mdyshop.model.Category;
import com.project.mdyshop.repository.CategoryRepository;
import com.project.mdyshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;
    @Autowired
    CategoryRepository categoryRepository;

    @PostMapping("/create")
    public ResponseEntity<Category> createCategory(@RequestBody CreateCategoryRequest request) {
        Category category = categoryService.createCategory(request);

        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @PostMapping("/createSub")
    public ResponseEntity<Category> createSubCategory(@RequestBody SubCategoryRequest request, @RequestHeader("Authorization")String jwt) {
        Category category = categoryService.createSubCategory(request);

        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Category>> getAllCategory(@RequestHeader("Authorization")String jwt) {
        List<Category> categories = categoryRepository.findAll();

        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
}
