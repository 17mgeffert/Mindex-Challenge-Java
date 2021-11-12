package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee create(Employee employee) {
        LOG.debug("Creating employee [{}]", employee);

        employee.setEmployeeId(UUID.randomUUID().toString());
        employeeRepository.insert(employee);

        return employee;
    }

    @Override
    public Employee read(String id) {
        LOG.debug("Creating employee with id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        return employee;
    }

    @Override
    public ReportingStructure reports(String id) {
        LOG.debug("Creating employee report with id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        return GenerateReportingStructure(employee);
    }


    /**
     * This method is designed to take in an employee object,
     * search through it for all its reports, and count all the
     * unique entries
     * @param employee
     * @return
     */
    private ReportingStructure GenerateReportingStructure(Employee employee){
        ReportingStructure reportingStructure = new ReportingStructure();
        reportingStructure.setEmployee(employee);
        int counter = 0;

        //Base case, if there exists no sub-reports
        if(employee.getDirectReports()==null){
            reportingStructure.setNumberOfReports(counter);
            return reportingStructure;
        }

        //Queue used to avoid a recursive solution
        Queue<Employee> queue = new LinkedList<>();
        queue.add(employee);
        while(!queue.isEmpty()){
            Employee newEmployee = queue.poll();

            List<Employee> reports = newEmployee.getDirectReports();
            if(reports !=null) {
                for (Employee e : reports) {
                    Employee employeeChild = employeeRepository.findByEmployeeId(e.getEmployeeId());
                    counter++;
                    queue.add(employeeChild);
                }
            }
        }

        reportingStructure.setNumberOfReports(counter);
        return reportingStructure;
    }

    @Override
    public Employee update(Employee employee) {
        LOG.debug("Updating employee [{}]", employee);

        return employeeRepository.save(employee);
    }
}
