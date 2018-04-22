package com.masdefect.domain.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "anomalies")
public class Anomaly implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Planet originPlanet;
    @ManyToOne
    private Planet teleportPlanet;
    @ManyToMany
    @JoinTable(name = "anomaly_victims",
    joinColumns = @JoinColumn(name = "anomaly_id",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "person_id", referencedColumnName = "id"))
    private Set<Person> persons;

    public Anomaly(Planet originPlanet, Planet teleportPlanet, Set<Person> persons) {
        this.originPlanet = originPlanet;
        this.teleportPlanet = teleportPlanet;
        this.persons = persons;
    }

    public Anomaly() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Planet getOriginPlanet() {
        return originPlanet;
    }

    public void setOriginPlanet(Planet originPlanet) {
        this.originPlanet = originPlanet;
    }

    public Planet getTeleportPlanet() {
        return teleportPlanet;
    }

    public void setTeleportPlanet(Planet teleportPlanet) {
        this.teleportPlanet = teleportPlanet;
    }

    public Set<Person> getPersons() {
        return persons;
    }

    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }
}
