package com.project.mdyshop.controller;

import com.project.mdyshop.model.Report;
import com.project.mdyshop.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/shop/report")
@PreAuthorize("hasRole('SHOP')")
public class ShopReportController {

    @Autowired
    ReportService reportService;

    @GetMapping("/{productId}")
    public ResponseEntity<List<Report>> getAllProductReport(@PathVariable Long productId) {
       List<Report> reports = reportService.getAllProductReports(productId);

       return new ResponseEntity<>(reports, HttpStatus.OK);
    }
}
