package com.project.mdyshop.controller;

import com.project.mdyshop.dto.request.CreateSaleRequest;
import com.project.mdyshop.exception.SaleException;
import com.project.mdyshop.model.Sale;
import com.project.mdyshop.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/sale")
public class AdminSaleController {

    @Autowired
    SaleService saleService;

    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN') || hasRole('SHOP') || hasRole('USER')")
    public ResponseEntity<List<Sale>> getAllSale() {
        List<Sale> sales = saleService.getAllSale();

        return new ResponseEntity<>(sales, HttpStatus.OK);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Sale> createSale(@RequestBody CreateSaleRequest request) {
        Sale sale = saleService.createSale(request);

        return new ResponseEntity<>(sale, HttpStatus.CREATED);
    }

    @PutMapping("/update/{saleId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Sale> updateSale(@PathVariable Long saleId) throws SaleException {
        Sale sale = saleService.updateStatus(saleId);

        return new ResponseEntity<>(sale, HttpStatus.ACCEPTED);
    }
}
