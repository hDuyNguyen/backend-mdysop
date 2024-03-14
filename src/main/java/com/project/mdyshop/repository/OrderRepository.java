package com.project.mdyshop.repository;

import com.project.mdyshop.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o where o.user.id = :userId")
    List<Order> findOrderByUserId(@Param("userId")Long userId);

    @Query("select o from Order o where o.shopId = :shopId")
    List<Order> findOrderByShopId(@Param("shopId")Long shopId);

    @Query("select o from Order o where o.shopId = :shopId and o.status = :status")
    List<Order> findUserOrderByStatus(@Param("shopId")Long shopId,
                                      @Param("status")String status);
}
