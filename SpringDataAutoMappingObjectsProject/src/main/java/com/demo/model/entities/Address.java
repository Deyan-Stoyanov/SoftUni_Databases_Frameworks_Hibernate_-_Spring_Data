package com.demo.model.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(targetEntity = City.class)
    private City city;
    @Column
    private String street;
    @OneToMany(mappedBy = "address")
    private List<Employee> employees;

    public Address(City city, String street, List<Employee> employees) {
        this.city = city;
        this.street = street;
        this.employees = employees;
    }

    public Address() {
    }

    public Long getId() {
        return id;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return this.getStreet() + ", " + this.getCity();
    }
}
