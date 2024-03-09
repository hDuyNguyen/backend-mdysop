package com.project.mdyshop.service;

import com.project.mdyshop.dto.request.CreateProductRequest;
import com.project.mdyshop.dto.request.UpdateProductRequest;
import com.project.mdyshop.exception.ProductException;
import com.project.mdyshop.model.Product;
import com.project.mdyshop.model.Shop;
import com.project.mdyshop.model.User;

import javax.sound.sampled.Port;
import java.util.List;

public interface ProductService {

    Product createProduct(CreateProductRequest request, Shop shop);

    Product updateProduct(UpdateProductRequest request, Long productId) throws Exception;

    Product deleteProduct(Long productId) throws ProductException;

    Product findProductById(Long productId) throws ProductException;

    List<Product> getAllProduct();

    List<Product> getAllAvailableProduct();
//
//    List<Product> findProductByTag(String tag);
//
//    List<Product> findProductByNameAndStatus(Long shopId, String name, String status);
//
//    List<Product> findProductFromShop(Long shopId) throws ProductException;
//
    void confirmProduct(Long productId) throws ProductException;
    void deniedProduct(Long productId) throws ProductException;

}
