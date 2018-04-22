package com.masdefect.domain.entities;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "planet")
public class Planet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Expose
    private String name;
    @ManyToOne
    @JoinColumn(name = "sun_id",referencedColumnName = "id")
    private Star sun;
    @ManyToOne
    @JoinColumn(name = "solar_system_id",referencedColumnName = "id")
    private SolarSystem solarSystem;
    @OneToMany(mappedBy = "homePlanet", cascade = CascadeType.ALL)
    private Set<Person> people;
    @OneToMany(mappedBy = "originPlanet")
    private Set<Anomaly> originAnomalies;
    @OneToMany(mappedBy = "teleportPlanet")
    private Set<Anomaly> teleportAnomalies;

    public Planet(String name, Star sun, SolarSystem solarSystem, Set<Person> people, Set<Anomaly> originAnomalies, Set<Anomaly> teleportAnomalies) {
        this.name = name;
        this.sun = sun;
        this.solarSystem = solarSystem;
        this.people = people;
        this.originAnomalies = originAnomalies;
        this.teleportAnomalies = teleportAnomalies;
    }

    public Planet(String name) {
        this.name = name;
    }

    public Planet() {
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

    public Star getSun() {
        return sun;
    }

    public void setSun(Star sun) {
        this.sun = sun;
    }

    public SolarSystem getSolarSystem() {
        return solarSystem;
    }

    public void setSolarSystem(SolarSystem solarSystem) {
        this.solarSystem = solarSystem;
    }

    public Set<Person> getPeople() {
        return people;
    }

    public void setPeople(Set<Person> people) {
        this.people = people;
    }

    public Set<Anomaly> getOriginAnomalies() {
        return originAnomalies;
    }

    public void setOriginAnomalies(Set<Anomaly> originAnomalies) {
        this.originAnomalies = originAnomalies;
    }

    public Set<Anomaly> getTeleportAnomalies() {
        return teleportAnomalies;
    }

    public void setTeleportAnomalies(Set<Anomaly> teleportAnomalies) {
        this.teleportAnomalies = teleportAnomalies;
    }
}
