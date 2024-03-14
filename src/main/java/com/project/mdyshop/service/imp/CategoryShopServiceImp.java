package com.project.mdyshop.service.imp;

import com.project.mdyshop.dto.request.AddProductToCategoryShop;
import com.project.mdyshop.dto.request.CategoryShopRequest;
import com.project.mdyshop.dto.request.RemoveProductFromCategoryShop;
import com.project.mdyshop.exception.CategoryShopException;
import com.project.mdyshop.model.CategoryShop;
import com.project.mdyshop.model.Product;
import com.project.mdyshop.model.Shop;
import com.project.mdyshop.repository.CategoryShopRepository;
import com.project.mdyshop.repository.ProductRepository;
import com.project.mdyshop.service.CategoryShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryShopServiceImp implements CategoryShopService {

    @Autowired
    CategoryShopRepository categoryShopRepository;
    @Autowired
    ProductRepository productRepository;
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

    @Override
    public CategoryShop addProductToCategoryShop(AddProductToCategoryShop request) {
        Optional<CategoryShop> opt = categoryShopRepository.findById(request.getCategoryShopId());

        if (opt.isPresent()) {
            CategoryShop categoryShop = opt.get();

            List<Long> listProductId = request.getProductsId();

            for (Long id: listProductId) {
                Optional<Product> productOpt = productRepository.findById(id);

                if (productOpt.isPresent()) {
                    Product product = productOpt.get();

                    if (!categoryShop.getProducts().contains(product)) {
                        categoryShop.getProducts().add(product);
                        product.getCategoryShops().add(categoryShop);

                        productRepository.save(product);
                    }
                }
            }
            return categoryShopRepository.save(categoryShop);
        }
        else {
            return null;
        }
    }

    @Override
    public CategoryShop removeProductFromCategoryShop(RemoveProductFromCategoryShop request) {
        Optional<CategoryShop> opt = categoryShopRepository.findById(request.getCategoryShopId());

        if (opt.isPresent()) {
            CategoryShop categoryShop = opt.get();
            Optional<Product> productOpt = productRepository.findById(request.getProductId());

            if (productOpt.isPresent()) {
                Product product = productOpt.get();

                if (categoryShop.getProducts().contains(product)) {

                    categoryShop.getProducts().remove(product);
                    product.getCategoryShops().remove(categoryShop);

                    productRepository.save(product);
                    return categoryShopRepository.save(categoryShop);
                }
                else {
                    // product khong thuoc category shop
                    return categoryShop;
                }
            }
            else {
                // khong tim thay product
                return categoryShop;
            }
        }
        else {
            // khong tim thay category shop
            return null;
        }
    }
}
