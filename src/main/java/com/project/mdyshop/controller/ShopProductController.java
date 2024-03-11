package com.project.mdyshop.controller;

import com.project.mdyshop.dto.request.CreateProductRequest;
import com.project.mdyshop.dto.request.UpdateProductRequest;
import com.project.mdyshop.exception.ProductException;
import com.project.mdyshop.exception.ShopException;
import com.project.mdyshop.exception.UserException;
import com.project.mdyshop.model.Product;
import com.project.mdyshop.model.Shop;
import com.project.mdyshop.model.User;
import com.project.mdyshop.service.ProductService;
import com.project.mdyshop.service.ShopService;
import com.project.mdyshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shop/products")

public class ShopProductController {

    @Autowired
    UserService userService;
    @Autowired
    ShopService shopService;
    @Autowired
    ProductService productService;

    @GetMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequest request,
                                                 @RequestHeader("Authorization")String jwt)
            throws UserException, ShopException {
        User user = userService.findUserByToken(jwt);
        Shop shop = shopService.findShopByUser(user.getId());

        Product product = productService.createProduct(request, shop);

        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@RequestHeader("Authorization")String jwt,
                                                 @PathVariable Long productId,
                                                 @RequestBody UpdateProductRequest request) throws ProductException {

        Product product = productService.updateProduct(request, productId);

        return new ResponseEntity<>(product, HttpStatus.ACCEPTED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Product>> getAllProduct(@RequestHeader("Authorization")String jwt)
            throws ProductException, UserException {
        User user = userService.findUserByToken(jwt);
        Shop shop = shopService.findShopByUser(user.getId());
        List<Product> products = productService.findProductFromShop(shop.getId());

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Product>> getProductShop(@RequestHeader("Authorization")String jwt,
                                                        @RequestParam(name = "name", required = false) String name,
                                                        @RequestParam(name = "status", required = false)String status)
            throws UserException {
        User user = userService.findUserByToken(jwt);
        Shop shop = shopService.findShopByUser(user.getId());

        List<Product> products = productService.findProductByNameAndStatus(shop.getId(), name, status);

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@RequestHeader("Authorization")String jwt,
                                                  @PathVariable Long productId)  throws ProductException{
        Product product = productService.findProductById(productId);

        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
