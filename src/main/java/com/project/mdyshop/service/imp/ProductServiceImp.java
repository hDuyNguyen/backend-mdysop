package com.project.mdyshop.service.imp;

import com.project.mdyshop.dto.request.CreateProductRequest;
import com.project.mdyshop.dto.request.UpdateProductRequest;
import com.project.mdyshop.dto.request.UpdateProductStatusRequest;
import com.project.mdyshop.exception.ProductException;
import com.project.mdyshop.model.Category;
import com.project.mdyshop.model.Product;
import com.project.mdyshop.model.Shop;
import com.project.mdyshop.repository.CategoryRepository;
import com.project.mdyshop.repository.ProductRepository;
import com.project.mdyshop.service.ProductService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProductRepository productRepository;
    @Override
    public Product createProduct(CreateProductRequest request, Shop shop) {
        Product product = new Product();

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setSizes(request.getSizes());
        product.setTags(request.getTags());
        product.setImageUrl(request.getImageUrl());
        product.setStatus("REQUEST");
        product.setShop(shop);

        Category topLevel = categoryRepository.findByName(request.getTopLevelCategory());

        Category secondLevel = categoryRepository.findByNameAndParentCategory(request.getSecondLevelCategory(), topLevel.getName());

        if (secondLevel != null) {
            Category thirdLevel = categoryRepository.findByNameAndParentCategory(request.getThirdLevelCategory(), secondLevel.getName());

            if (thirdLevel == null) {
                product.setCategory(secondLevel);
            } else {
            product.setCategory(thirdLevel);
            }
        }
        else {
            product.setCategory(topLevel);
        }

        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(UpdateProductRequest request, Long productId) throws ProductException{
        Optional<Product> opt = productRepository.findById(productId);

        if (opt.isPresent()) {
            Product product = opt.get();

            product.setName(request.getName());
            product.setDescription(request.getDescription());
            product.setSizes(request.getSizes());
            product.setTags(request.getTags());
            product.setImageUrl(request.getImageUrl());

            return productRepository.save(product);
        }
        else {
            throw new ProductException("Product not found with ID:" + productId);
        }
    }

    @Override
    public Product deleteProduct(Long productId) throws ProductException {
        Optional<Product> opt = productRepository.findById(productId);

        if (opt.isPresent()) {
            Product product = opt.get();

            product.setStatus("DELETE");
            return productRepository.save(product);
        }
        else {
            throw new ProductException("Product not found with ID:" + productId);
        }
    }

    @Override
    public Product findProductById(Long productId) throws ProductException {

        Optional<Product> opt = productRepository.findById(productId);

        if (opt.isPresent()) {
            return opt.get();
        }
        else {
            throw new ProductException("Product not found with ID:" + productId);
        }

    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getAllProductFromShop(Long shopId) throws ProductException{

        return productRepository.findByShopId(shopId);
    }

    @Override
    public List<Product> getAllAvailableProduct() {
        return productRepository.getAllAvailableProduct();
    }

    @Override
    public List<Product> findProductByCategory(Long categoryId) {
        return productRepository.findProductByCategory(categoryId);
    }

    @Override
    public List<Product> findProductByNameAndStatus(Long shopId, String name, String status) {

        return productRepository.getProductByNameAndStatus(shopId, name, status);
    }

    @Override
    public List<Product> findProductFromShop(Long shopId) throws ProductException {
        return null;
    }

    @Override
    public List<Product> filterProducts(Long categoryId, Double ratingValue) {
        return productRepository.findProductByRatingAndSubCategory(categoryId, ratingValue);
    }

    @Override
    public List<Product> filterProducts1(Long categoryId) {
        return productRepository.findProductByCategoryIdOrParentCategoryId(categoryId);
    }

    @Override
    public void confirmProduct(Long productId) throws ProductException {
        Optional<Product> opt = productRepository.findById(productId);

        if (opt.isPresent()) {
            Product product = opt.get();

            product.setStatus("AVAILABLE");
            productRepository.save(product);
        }
        else {
            throw new ProductException("Product not found with ID:" + productId);
        }
    }

    @Override
    public void deniedProduct(Long productId) throws ProductException {
        Optional<Product> opt = productRepository.findById(productId);

        if (opt.isPresent()) {
            Product product = opt.get();

            product.setStatus("DENY");
            productRepository.save(product);
        }
        else {
            throw new ProductException("Product not found with ID:" + productId);
        }
    }

    @Override
    public List<Product> findProductByCategoryShop(Long categoryShopId) {
        return productRepository.findProductByCategoryShop(categoryShopId);
    }

    @Override
    public List<Product> userFindProduct(Long shopId) {
        return productRepository.userFindProducts(shopId);
    }

    @Override
    public List<Product> findProductByNameAndShopId(String name, Long shopId) {
        return productRepository.findProductByNameAndShopId(name, shopId);
    }

    @Override
    public Product updateProductStatus(UpdateProductStatusRequest request, Long productId) {
        Optional<Product> opt = productRepository.findById(productId);

        if (opt.isPresent()) {
            Product product = opt.get();

            product.setStatus(request.getStatus());
            productRepository.save(product);
        }
        return null;
    }

    @Override
    public List<Product> getAllProductByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> getAllProductByCategory(Long categoryId) {
        return productRepository.findByCategory(categoryId);
    }
}
