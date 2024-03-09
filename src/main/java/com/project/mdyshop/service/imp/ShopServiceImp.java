package com.project.mdyshop.service.imp;

import com.project.mdyshop.dto.request.CreateShopRequest;
import com.project.mdyshop.exception.ShopException;
import com.project.mdyshop.model.Shop;
import com.project.mdyshop.model.User;
import com.project.mdyshop.repository.ShopRepository;
import com.project.mdyshop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShopServiceImp implements ShopService {
    @Autowired
    ShopRepository shopRepository;
    @Override
    public Shop createShop(CreateShopRequest request, User user) {
        Shop shop = new Shop();

        shop.setName(request.getName());
        shop.setImageUrl(request.getImageUrl());
        shop.setUser(user);

        return shopRepository.save(shop);
    }

    @Override
    public Shop updateShop(Long shopId, CreateShopRequest request) throws ShopException {
        Optional<Shop> opt = shopRepository.findById(shopId);

        if (opt.isPresent()) {
            Shop shop = opt.get();

            shop.setName(request.getName());
            shop.setImageUrl(request.getImageUrl());

            return shopRepository.save(shop);
        }
        throw new ShopException("Shop not found with ID: " + shopId);
    }
}
