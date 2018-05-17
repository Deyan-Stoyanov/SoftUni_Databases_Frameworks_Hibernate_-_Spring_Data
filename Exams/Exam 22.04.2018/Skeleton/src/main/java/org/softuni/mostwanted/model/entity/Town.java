package org.softuni.mostwanted.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "towns")
public class Town {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "town")
    private List<District> districts;

    @OneToMany(mappedBy = "homeTown")
    private List<Racer> racers;

    public Town(String name, List<District> districts, List<Racer> racers) {
        this.name = name;
        this.districts = districts;
        this.racers = racers;
    }

    public Town() {
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

    public List<District> getDistricts() {
        return districts;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }

    public List<Racer> getRacers() {
        return racers;
    }

    public void setRacers(List<Racer> racers) {
        this.racers = racers;
    }
}
