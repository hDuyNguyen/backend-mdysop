package com.project.mdyshop.controller;

import com.project.mdyshop.exception.ProductException;
import com.project.mdyshop.model.Product;
import com.project.mdyshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/product")
public class UserProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/{categoryId}")
    public ResponseEntity<List<Product>> getProductByCategory(@PathVariable Long categoryId) {
        List<Product> products = productService.findProductByCategory(categoryId);

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Product>> filterProducts(@RequestParam(name = "categoryId", required = false) Long categoryId,
                                                        @RequestParam(name = "ratingValue", required = false) Double ratingValue) {
        List<Product> products = productService.filterProducts(categoryId, ratingValue);

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/filter1")
    public ResponseEntity<List<Product>> filterProducts1(@RequestParam(name = "shopId", required = false) Long shopId,
                                                         @RequestParam(name = "categoryName")String categoryName) {
        List<Product> products = productService.filterProductByCategoryShop(shopId, categoryName);

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/details/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) throws ProductException {
        Product product = productService.findProductById(productId);

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/shop/{shopId}")
    public ResponseEntity<List<Product>> getProductByShopId(@PathVariable Long shopId) {
        List<Product> products = productService.userFindProduct(shopId);

        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
