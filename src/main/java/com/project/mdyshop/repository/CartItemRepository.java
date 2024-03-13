package com.project.mdyshop.repository;

import com.project.mdyshop.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    @Query("select ci from CartItem ci " +
            "where ci.product.id = :productId " +
            "and ci.cart.id = :cartId " +
            "and ci.size = :size")
    CartItem findByProductIdAndCartIdAndSize(@Param("productId")Long productId,
                                             @Param("cartId")Long cartId,
                                             @Param("size")String size);
}
