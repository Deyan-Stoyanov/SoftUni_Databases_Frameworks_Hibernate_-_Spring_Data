package com.demo.services.api;

import com.demo.model.entities.Employee;

import javax.transaction.Transactional;

@org.springframework.stereotype.Service
@Transactional
public interface EmployeeService {
    void registerEmployee(Employee employee);
}
