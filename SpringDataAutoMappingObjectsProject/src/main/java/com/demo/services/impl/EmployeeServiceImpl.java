package com.demo.services.impl;

import com.demo.dao.EmployeeDao;
import com.demo.model.entities.Employee;
import com.demo.services.api.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeDao employeeDao;

    @Autowired
    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public void registerEmployee(Employee employee) {
        this.employeeDao.saveAndFlush(employee);
    }
}
