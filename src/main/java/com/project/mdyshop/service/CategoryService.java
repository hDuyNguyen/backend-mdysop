package com.project.mdyshop.service;

import com.project.mdyshop.dto.request.CreateCategoryRequest;
import com.project.mdyshop.dto.request.SubCategoryRequest;
import com.project.mdyshop.exception.CategoryException;
import com.project.mdyshop.model.Category;

public interface CategoryService {

    Category createCategory(CreateCategoryRequest request);

    Category createSubCategory(SubCategoryRequest request);

    Category updateCategory(Long categoryId, CreateCategoryRequest request) throws CategoryException;

    void deleteCategory(Long categoryId) throws CategoryException;
}
