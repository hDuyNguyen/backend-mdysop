package com.project.mdyshop.repository;

import com.project.mdyshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p where p.status = 'AVAILABLE'")
    List<Product> getAllAvailableProduct();
}