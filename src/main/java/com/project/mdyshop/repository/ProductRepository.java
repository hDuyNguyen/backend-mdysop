package com.project.mdyshop.repository;

import com.project.mdyshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.sound.sampled.Port;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p where p.status = 'AVAILABLE'")
    List<Product> getAllAvailableProduct();

    @Query("select p from Product p where p.shop.id = :shopId")
    List<Product> findByShopId(@Param("shopId")Long shopId);

    @Query("select p from Product p where p.id = :productId " +
            "and (:name is null or p.name = :name) " +
            "and (:status is null or p.status = :status)")
    List<Product> getProductByNameAndStatus(@Param("productId")Long productId,
                                            @Param("name")String name,
                                            @Param("status")String status);
}
