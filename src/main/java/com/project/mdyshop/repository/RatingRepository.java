package com.project.mdyshop.repository;

import com.project.mdyshop.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    @Query("select r from Rating r where r.product.id = :productId")
    List<Rating> findRatingByProductId(@Param("productId")Long productId);

    @Query("select r from Rating r where r.product.shop.id = :shopId")
    List<Rating> findRatingByShopId(@Param("shopId")Long shopId);
}
