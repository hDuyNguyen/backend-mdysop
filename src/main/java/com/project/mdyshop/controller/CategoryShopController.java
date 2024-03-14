package com.project.mdyshop.controller;

import com.project.mdyshop.dto.request.AddProductToCategoryShop;
import com.project.mdyshop.dto.request.CategoryShopRequest;
import com.project.mdyshop.dto.request.RemoveProductFromCategoryShop;
import com.project.mdyshop.dto.response.ApiResponse;
import com.project.mdyshop.exception.CategoryShopException;
import com.project.mdyshop.exception.ShopException;
import com.project.mdyshop.exception.UserException;
import com.project.mdyshop.model.CategoryShop;
import com.project.mdyshop.model.Product;
import com.project.mdyshop.model.Shop;
import com.project.mdyshop.model.User;
import com.project.mdyshop.service.CategoryShopService;
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
@RequestMapping("/api/shop/categoryShop")
@PreAuthorize("hasRole('SHOP')")
public class CategoryShopController {

    @Autowired
    UserService userService;
    @Autowired
    ShopService shopService;
    @Autowired
    CategoryShopService categoryShopService;
    @Autowired
    ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<CategoryShop> createCategoryShop(@RequestBody CategoryShopRequest request,
                                                           @RequestHeader("Authorization") String jwt)
            throws UserException {
        User user = userService.findUserByToken(jwt);
        Shop shop = shopService.findShopByUser(user.getId());

        CategoryShop categoryShop = categoryShopService.createCategory(request, shop);

        return new ResponseEntity<>(categoryShop, HttpStatus.CREATED);
    }

    @PutMapping("/update/{categoryShopId}")
    public ResponseEntity<CategoryShop> updateCategoryShop(@RequestBody CategoryShopRequest request,
                                                           @PathVariable Long categoryShopId)
        throws CategoryShopException {
        CategoryShop categoryShop = categoryShopService.updateCategory(request, categoryShopId);

        return new ResponseEntity<>(categoryShop, HttpStatus.OK);
    }

    @PutMapping("/updateStatus/{categoryShopId}")
    public ResponseEntity<ApiResponse> updateStatusCategory(@PathVariable Long categoryShopId) throws CategoryShopException {
        categoryShopService.updateStatusCategory(categoryShopId);

        ApiResponse response = new ApiResponse();

        response.setMessage("Status has been updated");
        response.setStatus(true);

        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{categoryShopId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long categoryShopId) throws CategoryShopException {
        categoryShopService.deleteCategory(categoryShopId);

        ApiResponse response = new ApiResponse();

        response.setMessage("Category has been deleted");
        response.setStatus(true);

        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryShop>> getAllCategoryShop(@RequestHeader("Authorization") String jwt) throws UserException {
        User user = userService.findUserByToken(jwt);
        Shop shop = shopService.findShopByUser(user.getId());

        List<CategoryShop> categoryShops = categoryShopService.getAllCategory(shop.getId());

        return new ResponseEntity<>(categoryShops, HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<CategoryShop> addProductToCategoryShop(@RequestBody AddProductToCategoryShop request) {
        CategoryShop categoryShop = categoryShopService.addProductToCategoryShop(request);

        return new ResponseEntity<>(categoryShop, HttpStatus.CREATED);
    }

    @DeleteMapping("/product/delete")
    public ResponseEntity<CategoryShop> removeProductFromCategoryShop(@RequestBody RemoveProductFromCategoryShop request) {
        CategoryShop categoryShop = categoryShopService.removeProductFromCategoryShop(request);

        return new ResponseEntity<>(categoryShop, HttpStatus.OK);
    }

    @GetMapping("/product/{categoryShopId}")
    public ResponseEntity<List<Product>> getAllProductCategory(@PathVariable Long categoryShopId) {
        List<Product> products = productService.findProductByCategory(categoryShopId);

        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
