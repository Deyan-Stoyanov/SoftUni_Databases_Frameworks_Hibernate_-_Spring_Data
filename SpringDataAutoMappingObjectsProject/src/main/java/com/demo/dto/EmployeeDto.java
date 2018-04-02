package com.demo.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class EmployeeDto implements Serializable {
    private String firstName;
    private String lastName;
    private String addressCityName;
    private BigDecimal salary;

    public EmployeeDto(String firstName, String lastName, String addressCityName, BigDecimal salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.addressCityName = addressCityName;
        this.salary = salary;
    }

    public EmployeeDto() {
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

    public String getAddressCityName() {
        return addressCityName;
    }

    public void setAddressCityName(String addressCityName) {
        this.addressCityName = addressCityName;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return this.getFirstName() + " " + this.getLastName();
    }
}
