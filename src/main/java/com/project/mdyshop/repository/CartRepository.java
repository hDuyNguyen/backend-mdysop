package com.project.mdyshop.repository;

import com.project.mdyshop.model.Cart;
import com.project.mdyshop.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query("select c from Cart c where c.shopId = :shopId and c.user.id = :userId")
    Cart findCartByShopIdAndUserId(@Param("shopId") Long shopId, @Param("userId") Long UserId);

    @Query("select ci from CartItem ci join ci.cart c join c.user u where u.id = :userId")
    List<CartItem> findCartItemByUserId(@Param("userId")Long userId);

    @Query("select c from Cart c where c.user.id = :userId")
    List<Cart> findCartByUserId(@Param("userId")Long userId);

}
