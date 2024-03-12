package com.project.mdyshop.controller;

import com.project.mdyshop.dto.request.CreateCategoryRequest;
import com.project.mdyshop.dto.request.SubCategoryRequest;
import com.project.mdyshop.dto.request.UpdateCategoryRequest;
import com.project.mdyshop.dto.response.ApiResponse;
import com.project.mdyshop.exception.CategoryException;
import com.project.mdyshop.model.Category;
import com.project.mdyshop.repository.CategoryRepository;
import com.project.mdyshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Category> createCategory(@RequestBody CreateCategoryRequest request) {
        Category category = categoryService.createCategory(request);

        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @PostMapping("/createSub")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Category> createSubCategory(@RequestBody SubCategoryRequest request) {
        Category category = categoryService.createSubCategory(request);

        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Category>> getAllCategory() {
        List<Category> categories = categoryRepository.findAll();

        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/parent")
    public ResponseEntity<List<Category>> getAllParentCategory() {
        List<Category> categories = categoryService.getParentCategory();

        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/parent/{categoryId}")
    public ResponseEntity<List<Category>> getAllSubCategory(@PathVariable Long categoryId) {
        List<Category> categories = categoryService.getSubCategory(categoryId);

        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PutMapping("/update/{categoryId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Category> updateCategory(@RequestBody UpdateCategoryRequest request, @PathVariable Long categoryId) throws CategoryException{
        Category category = categoryService.updateCategory(categoryId, request);

        return new ResponseEntity<>(category, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{categoryId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long categoryId) throws CategoryException {
        categoryService.deleteCategory(categoryId);

        ApiResponse response = new ApiResponse();

        response.setMessage("Delete Successfully");
        response.setStatus(true);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
