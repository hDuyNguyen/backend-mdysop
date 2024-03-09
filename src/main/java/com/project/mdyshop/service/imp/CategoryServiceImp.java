package com.project.mdyshop.service.imp;

import com.project.mdyshop.dto.request.CreateCategoryRequest;
import com.project.mdyshop.exception.CategoryException;
import com.project.mdyshop.model.Category;
import com.project.mdyshop.repository.CategoryRepository;
import com.project.mdyshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public Category createCategory(CreateCategoryRequest request) {
        Category category = new Category();

        category.setName(request.getName());
        category.setDescription(request.getDescription());
        category.setImageUrl(request.getImageUrl());
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Long categoryId, CreateCategoryRequest request) throws CategoryException {

        Optional<Category> opt = categoryRepository.findById(categoryId);

        if (opt.isPresent()) {
            Category category = opt.get();
            category.setName(request.getName());
            category.setDescription(request.getDescription());
            category.setImageUrl(request.getImageUrl());

            return categoryRepository.save(category);
        }
        else {
            throw new CategoryException("Category not found with ID: " + categoryId);
        }
    }

    @Override
    public void deleteCategory(Long categoryId) throws CategoryException {
        Optional<Category> opt = categoryRepository.findById(categoryId);

        if (opt.isPresent()) {
            Category category = opt.get();
            categoryRepository.deleteById(category.getId());
        }
        throw new CategoryException("Category not found with ID: " + categoryId);

    }
}
