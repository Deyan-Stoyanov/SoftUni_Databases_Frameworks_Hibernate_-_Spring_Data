package org.softuni.mostwanted.model.entity;


import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "races")
public class Race {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, columnDefinition = "INT default '0'")
    private Integer laps;

    @ManyToOne(cascade = CascadeType.ALL)
    private District district;

    @OneToMany(mappedBy = "race", cascade = CascadeType.ALL)
    private List<RaceEntry> entries;

    public Race(Integer brand, District district, List<RaceEntry> entries) {
        this.laps = brand;
        this.district = district;
        this.entries = entries;
    }

    public Race() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLaps() {
        return laps;
    }

    public void setLaps(Integer laps) {
        this.laps = laps;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public List<RaceEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<RaceEntry> entries) {
        this.entries = entries;
    }
}
