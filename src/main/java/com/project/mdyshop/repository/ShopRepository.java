package com.project.mdyshop.repository;

import com.project.mdyshop.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service
public interface ShopRepository extends JpaRepository<Shop, Long> {

    @Query("select s from Shop s where s.user.id = :userId")
    Shop findShopByUserId(@Param("userId") Long userId);


}
