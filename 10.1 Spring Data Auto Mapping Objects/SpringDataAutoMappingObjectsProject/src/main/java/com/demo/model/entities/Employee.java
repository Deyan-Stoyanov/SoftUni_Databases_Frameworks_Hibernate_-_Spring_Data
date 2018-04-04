package com.demo.model.entities;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private Date birthDate;
    @ManyToOne(targetEntity = Address.class)
    private Address address;
    @Column
    private BigDecimal salary;
    @Column
    private boolean isOnHoliday;
    @ManyToOne
    private Employee manager;
    @OneToMany(mappedBy = "manager")
    private List<Employee> employees;

    public Employee(String firstName, String lastName, Date birthDate, Address address, BigDecimal salary, boolean isOnHoliday, Employee manager, List<Employee> employees) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.address = address;
        this.salary = salary;
        this.isOnHoliday = isOnHoliday;
        this.manager = manager;
        this.employees = employees;
    }

    public Employee() {
    }

    public Long getId() {
        return id;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public boolean isOnHoliday() {
        return isOnHoliday;
    }

    public void setOnHoliday(boolean onHoliday) {
        isOnHoliday = onHoliday;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return this.getFirstName() + " " + this.getLastName();
    }
}
