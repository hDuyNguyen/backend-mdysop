package com.project.mdyshop.controller;

import com.project.mdyshop.dto.response.ApiResponse;
import com.project.mdyshop.exception.ProductException;
import com.project.mdyshop.model.Product;
import com.project.mdyshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/product")
@PreAuthorize("hasRole('ADMIN')")
public class AdminProductController {

    @Autowired
    ProductService productService;

    @PutMapping("/confirm/accepted/{productId}")
    public ResponseEntity<ApiResponse> confirmProduct(@PathVariable Long productId) throws ProductException {
        productService.confirmProduct(productId);

        ApiResponse response = new ApiResponse();
        response.setMessage("Accept Successfully");
        response.setStatus(true);

        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PutMapping("/confirm/denied/{productId}")
    public ResponseEntity<ApiResponse> deniedProduct(@PathVariable Long productId) throws ProductException {
        productService.deniedProduct(productId);

        ApiResponse response = new ApiResponse();
        response.setMessage("Denied Successfully");
        response.setStatus(true);

        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Product>> getAllProduct() {
        List<Product> products = productService.getAllProduct();

        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
