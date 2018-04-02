package com.demo.dao;

import com.demo.model.entities.Address;
import com.demo.model.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDao extends JpaRepository<Employee, Long> {
    void findEmployeeByAddress_CityName(String cityName);
}
