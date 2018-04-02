package com.demo.util;

import com.demo.dto.EmployeeDto;
import com.demo.model.entities.Employee;
import org.modelmapper.ModelMapper;

public class Mapper {

    public Mapper() {
    }

    public static EmployeeDto convertEmployee(Employee employee){
        ModelMapper modelMapper = new ModelMapper();
        EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
        return employeeDto;
    }
}
