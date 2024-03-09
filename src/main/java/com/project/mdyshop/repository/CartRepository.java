package com.project.mdyshop.repository;

import com.project.mdyshop.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query("select c from Cart c where c.shopId = :shopId and c.user.id = :userId")
    Cart findCartByShopIdAndUserId(@Param("shopId") Long shopId, @Param("userId") Long UserId);
}
