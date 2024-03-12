package com.project.mdyshop.service.imp;

import com.project.mdyshop.dto.request.CategoryShopRequest;
import com.project.mdyshop.exception.CategoryShopException;
import com.project.mdyshop.model.CategoryShop;
import com.project.mdyshop.model.Shop;
import com.project.mdyshop.repository.CategoryShopRepository;
import com.project.mdyshop.service.CategoryShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryShopServiceImp implements CategoryShopService {

    @Autowired
    CategoryShopRepository categoryShopRepository;
    @Override
    public CategoryShop createCategory(CategoryShopRequest request, Shop shop) {
        CategoryShop categoryShop = new CategoryShop();

        categoryShop.setShop(shop);
        categoryShop.setName(request.getName());
        categoryShop.setDescription(request.getDescription());
        categoryShop.setStatus("AVAILABLE");
        return categoryShopRepository.save(categoryShop);
    }

    @Override
    public CategoryShop updateCategory(CategoryShopRequest request, Long categoryShopId) throws CategoryShopException {

        Optional<CategoryShop> opt = categoryShopRepository.findById(categoryShopId);
        if (opt.isPresent()) {
            CategoryShop categoryShop = opt.get();

            categoryShop.setName(request.getName());
            categoryShop.setDescription(request.getDescription());

            return categoryShopRepository.save(categoryShop);
        }

        throw new CategoryShopException("Category Shop not found with ID: " + categoryShopId);
    }

    @Override
    public void updateStatusCategory(Long categoryShopId) throws CategoryShopException {
        Optional<CategoryShop> opt = categoryShopRepository.findById(categoryShopId);
        if (opt.isPresent()) {
            CategoryShop categoryShop = opt.get();

            if (categoryShop.getStatus().equals("AVAILABLE")) {
                categoryShop.setStatus("HIDE");
            }
            else {
                categoryShop.setStatus("AVAILABLE");
            }
            categoryShopRepository.save(categoryShop);
        }

        throw new CategoryShopException("Category Shop not found with ID: " + categoryShopId);
    }

    @Override
    public void deleteCategory(Long categoryShopId) throws CategoryShopException {
        Optional<CategoryShop> opt = categoryShopRepository.findById(categoryShopId);
        if (opt.isPresent()) {
            CategoryShop categoryShop = opt.get();
            categoryShopRepository.deleteById(categoryShop.getId());
        }

        throw new CategoryShopException("Category Shop not found with ID: " + categoryShopId);
    }

    @Override
    public List<CategoryShop> getAllCategory(Long shopId) {
        return categoryShopRepository.findAllCategoryShop(shopId);
    }
}
