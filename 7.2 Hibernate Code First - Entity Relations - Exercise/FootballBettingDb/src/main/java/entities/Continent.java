package entities;

import javax.persistence.*;

@Entity
@Table(name = "continents")
public class Continent {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    private String name;

    public Continent(String name) {
        this.name = name;
    }

    public Continent() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
