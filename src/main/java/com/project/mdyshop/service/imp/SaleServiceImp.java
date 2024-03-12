package com.project.mdyshop.service.imp;

import com.project.mdyshop.dto.request.CreateSaleRequest;
import com.project.mdyshop.exception.SaleException;
import com.project.mdyshop.model.Sale;
import com.project.mdyshop.repository.SaleRepository;
import com.project.mdyshop.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SaleServiceImp implements SaleService {
    @Autowired
    SaleRepository saleRepository;

    @Override
    public Sale createSale(CreateSaleRequest request) {
        Sale sale = new Sale();

        sale.setName(request.getName());
        sale.setDescription(request.getDescription());
        sale.setDiscountNumber(request.getDiscountNumber());
        sale.setDiscountType(request.getDiscountType());
        sale.setTimeStart(LocalDateTime.parse(request.getTimeStart()));
        sale.setTimeEnd(LocalDateTime.parse(request.getTimeEnd()));
        sale.setStatus("AVAILABLE");
        return saleRepository.save(sale);
    }

    @Override
    public Sale updateStatus(Long saleId) throws SaleException {
        Optional<Sale> opt = saleRepository.findById(saleId);

        if (opt.isPresent()) {
            Sale sale = opt.get();
            if (sale.getStatus().equals("AVAILABLE")) {
                sale.setStatus("HIDE");

                return saleRepository.save(sale);
            }
            if (sale.getStatus().equals("HIDE")) {
                sale.setStatus("AVAILABLE");

                return saleRepository.save(sale);
            }
        }
        throw new SaleException("Sale not found with ID: " + saleId);
    }

    @Override
    public List<Sale> getAllSale() {
        return saleRepository.findAll();
    }
}
