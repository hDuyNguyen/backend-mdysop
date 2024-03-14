package com.project.mdyshop.service;

import com.project.mdyshop.dto.request.AddProductToCategoryShop;
import com.project.mdyshop.dto.request.CategoryShopRequest;
import com.project.mdyshop.dto.request.CreateCategoryRequest;
import com.project.mdyshop.dto.request.RemoveProductFromCategoryShop;
import com.project.mdyshop.exception.CategoryShopException;
import com.project.mdyshop.model.CategoryShop;
import com.project.mdyshop.model.Shop;

import java.util.List;

public interface CategoryShopService {

    CategoryShop createCategory(CategoryShopRequest request, Shop shop);

    CategoryShop updateCategory(CategoryShopRequest request, Long categoryShopId) throws CategoryShopException;

    void updateStatusCategory(Long categoryShopId) throws CategoryShopException;

    void deleteCategory(Long categoryShopId) throws CategoryShopException;

    List<CategoryShop> getAllCategory(Long shopId);

    CategoryShop addProductToCategoryShop(AddProductToCategoryShop request);

    CategoryShop removeProductFromCategoryShop(RemoveProductFromCategoryShop request);
}
