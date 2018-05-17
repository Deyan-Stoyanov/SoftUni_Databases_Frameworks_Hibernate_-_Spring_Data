package org.softuni.mostwanted.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "racers")
public class Racer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column
    private Integer age;

    @Column
    private BigDecimal bounty;

    @ManyToOne(cascade = CascadeType.ALL)
    private Town homeTown;

    @OneToMany(mappedBy = "racer")
    private List<Car> cars;

    public Racer() {
    }

    public Racer(String name, Integer age, BigDecimal bounty, Town homeTown, List<Car> cars) {
        this.name = name;
        this.age = age;
        this.bounty = bounty;
        this.homeTown = homeTown;
        this.cars = cars;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public BigDecimal getBounty() {
        return bounty;
    }

    public void setBounty(BigDecimal bounty) {
        this.bounty = bounty;
    }

    public Town getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(Town homeTown) {
        this.homeTown = homeTown;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
