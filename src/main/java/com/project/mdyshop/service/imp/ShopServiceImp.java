package com.project.mdyshop.service.imp;

import com.project.mdyshop.dto.request.CreateShopRequest;
import com.project.mdyshop.exception.ShopException;
import com.project.mdyshop.model.Role;
import com.project.mdyshop.model.Shop;
import com.project.mdyshop.model.User;
import com.project.mdyshop.repository.ShopRepository;
import com.project.mdyshop.repository.UserRepository;
import com.project.mdyshop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ShopServiceImp implements ShopService {
    @Autowired
    ShopRepository shopRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    public Shop createShop(CreateShopRequest request, User user) {
        Shop shop = shopRepository.findShopByUser(user.getId());
        if (shop == null) {
            Shop createShop = new Shop();

            createShop.setName(request.getName());
            createShop.setImageUrl(request.getImageUrl());
            createShop.setUser(user);
            createShop.setCreateAt(LocalDateTime.now());
            createShop.setStatus("ONLINE");
            user.getRoles().add(Role.SHOP);
            userRepository.save(user);

            return shopRepository.save(createShop);
        }
        return shop;
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

    @Override
    public Shop findShopByUser(Long userId) {
        return shopRepository.findShopByUser(userId);
    }

    @Override
    public List<Shop> getAllShop() {
        return shopRepository.findAll();
    }

    @Override
    public Shop findById(Long shopId) {
        Optional<Shop> opt = shopRepository.findById(shopId);

        return opt.orElse(null);
    }
}
