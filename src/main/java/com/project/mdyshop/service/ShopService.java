package com.project.mdyshop.service;

import com.project.mdyshop.dto.request.CreateShopRequest;
import com.project.mdyshop.exception.ShopException;
import com.project.mdyshop.model.Shop;
import com.project.mdyshop.model.User;

import java.util.List;

public interface ShopService {

    Shop createShop(CreateShopRequest request, User user);

    Shop updateShop(Long shopId, CreateShopRequest request) throws ShopException;

    Shop findShopByUser(Long userId);

    List<Shop> getAllShop();

    Shop findById(Long shopId);
}
