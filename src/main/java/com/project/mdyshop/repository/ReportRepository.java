package com.project.mdyshop.repository;

import com.project.mdyshop.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {

    @Query("select r from Report r where r.product.id = :productId")
    List<Report> getProductReports(@Param("productId") Long productId);
}
