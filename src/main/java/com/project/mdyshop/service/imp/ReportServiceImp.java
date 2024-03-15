package com.project.mdyshop.service.imp;

import com.project.mdyshop.dto.request.CreateReportRequest;
import com.project.mdyshop.model.Product;
import com.project.mdyshop.model.Report;
import com.project.mdyshop.model.User;
import com.project.mdyshop.repository.ProductRepository;
import com.project.mdyshop.repository.ReportRepository;
import com.project.mdyshop.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportServiceImp implements ReportService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ReportRepository reportRepository;

    @Override
    public Report createReport(CreateReportRequest request, User user) {
        Optional<Product> opt = productRepository.findById(request.getProductId());

        if (opt.isPresent()) {
            Report report = new Report();

            report.setUser(user);
            report.setMessage(request.getMessage());
            report.setReportType(request.getReportType());
            report.setProduct(opt.get());

            return reportRepository.save(report);
        }
        return null;
    }

    @Override
    public List<Report> getAllReport() {
        return reportRepository.findAll();
    }

    @Override
    public List<Report> getAllProductReports(Long productId) {
        return reportRepository.getProductReports(productId);
    }
}
