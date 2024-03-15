package com.project.mdyshop.repository;

import com.project.mdyshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import javax.sound.sampled.Port;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p where p.status = 'AVAILABLE'")
    List<Product> getAllAvailableProduct();

    @Query("select p from Product p where p.shop.id = :shopId")
    List<Product> findByShopId(@Param("shopId")Long shopId);

    @Query("select p from Product p where p.id = :productId " +
            "and (:name is null or p.name like concat('%', :name, '%')) " +
            "and (:status is null or p.status = :status)")
    List<Product> getProductByNameAndStatus(@Param("productId")Long productId,
                                            @Param("name")String name,
                                            @Param("status")String status);


    @Query(value =
            "with recursive CategoryTree as(" +
                    "select id, parent_category_id, name " +
                    "from category where id = :categoryId " +
                    "union all " +
                    "select c.id, c.parent_category_id, c.name " +
                    "from category c " +
                    "join CategoryTree ct on c.parent_category_id = ct.id" +
                    ")" +
                    "select p.* " +
                    "from product p " +
                    "join CategoryTree ct " +
                    "on p.category_id = ct.id or p.category_id = :categoryId " +
                    "where p.status not in ('REQUEST', 'DENY')",
            nativeQuery = true)
    List<Product> findProductByCategory(@Param("categoryId") Long categoryId);

    @Query("select p from Product p " +
            "join p.category c " +
            "left join p.ratings r " +
            "where :categoryId is null or p.category.id = :categoryId " +
            "or (c.parentCategory.id = :categoryId and p.category.id = c.id) " +
            "group by p " +
            "having (:ratingValue is null or avg(r.rating) > :ratingValue)" +
            "and (p.status is null or p.status not in ('REQUEST', 'DENY'))")
    List<Product> findProductByRatingAndSubCategory(@Param("categoryId")Long categoryId,
                                                    @Param("ratingValue")Double ratingValue);

    @Query("select p from Product p " +
            "join p.category c " +
            "where (:categoryId is null or p.category.id = :categoryId " +
            "or (c.parentCategory.id = :categoryId and p.category.id = c.id))")
    List<Product> findProductByCategoryIdOrParentCategoryId(@Param("categoryId")Long categoryId);

    @Query("select p from Product p where p.shop.id = :shopId " +
            "and (p.status is null or p.status not in ('REQUEST', 'DENY'))")
    List<Product> userFindProducts(@Param("shopId")Long shopId);

    @Query("select p from Product p join CategoryShop cs where cs.id = :categoryShopId")
    List<Product> findProductByCategoryShop(@Param("categoryShopId")Long categoryShopId);

    @Query("select p from Product p " +
            "where p.shop.id = :shopId " +
            "and :name is null or lower(p.name) like lower(concat('%', :name, '%')) " +
            "and (p.status is null or p.status not in ('REQUEST', 'DENY'))")
    List<Product> findProductByNameAndShopId(@Param("name")String name,
                                             @Param("shopId")Long shopId);

//    @Query("select p from Product p join p.tags t where t.name = :tag and (p.status is null or p.status not in ('REQUEST', 'DENY'))")
//    List<Product> findProductByTag(@Param("tag")String tag);

    @Query("select p from Product p where " +
            "(:name is null or p.name like concat('%', :name, '%')) " +
            "and p.status = 'AVAILABLE'")
    List<Product> findByName(@Param("name")String name);

    @Query("select p from Product p where p.category.id = :categoryId")
    List<Product> findByCategory(@Param("categoryId")Long category);

}
