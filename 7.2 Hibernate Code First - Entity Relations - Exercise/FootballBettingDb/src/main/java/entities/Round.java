package entities;

import javax.persistence.*;

@Entity
@Table(name = "rounds")
public class Round {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    private String name;

    public Round(String name) {
        this.name = name;
    }

    public Round() {
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
