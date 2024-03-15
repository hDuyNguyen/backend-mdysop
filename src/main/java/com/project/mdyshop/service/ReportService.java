package com.project.mdyshop.service;

import com.project.mdyshop.dto.request.CreateReportRequest;
import com.project.mdyshop.model.Report;
import com.project.mdyshop.model.User;

import java.util.List;

public interface ReportService {

    Report createReport(CreateReportRequest request, User user);

    List<Report> getAllReport();

    List<Report> getAllProductReports(Long productId);
}
