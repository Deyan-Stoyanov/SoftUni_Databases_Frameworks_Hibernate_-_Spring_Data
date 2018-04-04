package com.demo.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class ManagerDto implements Serializable {
    private String firstName;
    private String lastName;
    private BigDecimal salary;
    private List<EmployeeDto> employees;

    public ManagerDto(String firstName, String lastName, BigDecimal salary, List<EmployeeDto> employees) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.employees = employees;
    }

    public ManagerDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public List<EmployeeDto> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeDto> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return this.getFirstName() + " " + this.getLastName();
    }
}
