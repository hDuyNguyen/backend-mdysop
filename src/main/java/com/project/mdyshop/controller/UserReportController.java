package com.project.mdyshop.controller;

import com.project.mdyshop.dto.request.CreateReportRequest;
import com.project.mdyshop.exception.UserException;
import com.project.mdyshop.model.Report;
import com.project.mdyshop.model.User;
import com.project.mdyshop.service.ReportService;
import com.project.mdyshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/report")
@PreAuthorize("hasRole('USER')")
public class UserReportController {

    @Autowired
    UserService userService;
    @Autowired
    ReportService reportService;

    @PostMapping("/new")
    public ResponseEntity<Report> createReport(@RequestHeader("Authorization")String jwt,
                                               @RequestBody CreateReportRequest request)
        throws UserException {

        User user = userService.findUserByToken(jwt);
        Report report = reportService.createReport(request, user);

        return new ResponseEntity<>(report, HttpStatus.CREATED);
    }
}
