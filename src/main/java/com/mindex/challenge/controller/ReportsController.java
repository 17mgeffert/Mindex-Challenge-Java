package com.mindex.challenge.controller;

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReportsController {
    private static final Logger LOG = LoggerFactory.getLogger(ReportsController.class);

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/reports/{id}")
    public ReportingStructure reports(@PathVariable String id) {
        LOG.debug("Received employee create request for id [{}]", id);
        return employeeService.reports(id);
    }
}
