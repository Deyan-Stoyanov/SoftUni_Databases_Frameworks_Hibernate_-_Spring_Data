package entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "countries")
public class Country {
    @Id
    @Column(name = "id")
    private String id = "";

    @Basic
    private String name;

    @ManyToMany
    @JoinTable(name = "countries_continents",
            joinColumns = @JoinColumn(name = "country_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "continent_id", referencedColumnName = "id"))
    private List<Continent> continent;

    public Country(String id, String name, List<Continent> continent) {
        this.id = id;
        this.name = name;
        this.continent = continent;
    }

    public Country() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Continent> getContinent() {
        return continent;
    }

    public void setContinent(List<Continent> continent) {
        this.continent = continent;
    }
}
