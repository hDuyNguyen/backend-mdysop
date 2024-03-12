package com.project.mdyshop.repository;

import com.project.mdyshop.model.CategoryShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryShopRepository extends JpaRepository<CategoryShop, Long> {

    @Query("select cs from CategoryShop cs where cs.shop.id = :shopId")
    List<CategoryShop> findAllCategoryShop(@Param("shopId")Long shopId);
}
