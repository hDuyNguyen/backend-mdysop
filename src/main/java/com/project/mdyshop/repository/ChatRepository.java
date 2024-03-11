package com.project.mdyshop.repository;

import com.project.mdyshop.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {

    @Query("select c from Chat c where c.user.id = :userId and c.shop.id = :shopId")
    Chat findChatByUserAndShop(@Param("userId") Long userid, @Param("shopId")Long shopId);

    @Query("select c from Chat c where c.user.id = :userId")
    List<Chat> findUserChats(@Param("userId") Long userId);

    @Query("select c from Chat c where c.shop.id = :shopId")
    List<Chat> finShopChats(@Param("shopId") Long shopId);
}
