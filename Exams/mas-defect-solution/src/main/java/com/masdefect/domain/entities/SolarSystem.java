package com.masdefect.domain.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "solar_systems")
public class SolarSystem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @OneToMany(mappedBy = "solarSystem", cascade = CascadeType.ALL)
    private Set<Star> stars;
    @OneToMany(mappedBy = "solarSystem")
    private Set<Planet> planets;
    public SolarSystem(String name, HashSet<Star> stars, Set<Planet> planets) {
        this.name = name;
        this.stars = stars;
        this.planets = planets;
    }

    public SolarSystem() {
        this.stars = new HashSet<>();
        this.planets = new HashSet<>();
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

    public Set<Star> getStars() {
        return stars;
    }

    public void setStars(Set<Star> stars) {
        this.stars = stars;
    }

    public Set<Planet> getPlanets() {
        return planets;
    }

    public void setPlanets(Set<Planet> planets) {
        this.planets = planets;
    }
}
