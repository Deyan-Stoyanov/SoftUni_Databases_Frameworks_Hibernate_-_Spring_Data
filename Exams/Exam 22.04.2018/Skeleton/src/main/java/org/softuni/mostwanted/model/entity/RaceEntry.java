package org.softuni.mostwanted.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "race_entries")
public class RaceEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Boolean hasFinished;

    @Column
    private Double finishTime;

    @ManyToOne
    private Car car;

    @ManyToOne
    private Racer racer;

    @ManyToOne(cascade = CascadeType.ALL)
    private Race race;

    public RaceEntry(Boolean hasFinished, Double finishTime, Car car, Racer racer, Race race) {
        this.hasFinished = hasFinished;
        this.finishTime = finishTime;
        this.car = car;
        this.racer = racer;
        this.race = race;
    }

    public RaceEntry() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getHasFinished() {
        return hasFinished;
    }

    public void setHasFinished(Boolean hasFinished) {
        this.hasFinished = hasFinished;
    }

    public Double getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Double finishTime) {
        this.finishTime = finishTime;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Racer getRacer() {
        return racer;
    }

    public void setRacer(Racer racer) {
        this.racer = racer;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }
}
