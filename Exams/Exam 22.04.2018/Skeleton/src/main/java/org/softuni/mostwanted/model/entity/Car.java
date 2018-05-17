package org.softuni.mostwanted.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String model;

    @Column
    private BigDecimal price;

    @Column(nullable = false)
    private Integer yearOfProduction;

    @Column
    private Double maxSpeed;

    @Column
    private Double zeroToSixty;

    @ManyToOne(cascade = CascadeType.ALL)
    private Racer racer;

    public Car(String brand, String model, BigDecimal price, Integer yearOfProduction, Double maxSpeed, Double zeroToSixty, Racer racer) {
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.yearOfProduction = yearOfProduction;
        this.maxSpeed = maxSpeed;
        this.zeroToSixty = zeroToSixty;
        this.racer = racer;
    }

    public Car() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(Integer yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public Double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(Double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public Double getZeroToSixty() {
        return zeroToSixty;
    }

    public void setZeroToSixty(Double zeroToSixty) {
        this.zeroToSixty = zeroToSixty;
    }

    public Racer getRacer() {
        return racer;
    }

    public void setRacer(Racer racer) {
        this.racer = racer;
    }
}
