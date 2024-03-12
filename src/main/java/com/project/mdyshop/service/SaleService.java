package com.project.mdyshop.service;

import com.project.mdyshop.dto.request.CreateSaleRequest;
import com.project.mdyshop.exception.SaleException;
import com.project.mdyshop.model.Sale;

import java.util.List;

public interface SaleService {

    Sale createSale(CreateSaleRequest request);

    Sale updateStatus(Long saleId) throws SaleException;

    List<Sale> getAllSale();
}
