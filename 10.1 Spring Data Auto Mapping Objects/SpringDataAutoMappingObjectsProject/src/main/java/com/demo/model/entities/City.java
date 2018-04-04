package com.demo.model.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cities")
public class City {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @OneToMany(mappedBy = "city")
    private List<Address> addresses;

    public City(String name, List<Address> addresses) {
        this.name = name;
        this.addresses = addresses;
    }

    public City() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
