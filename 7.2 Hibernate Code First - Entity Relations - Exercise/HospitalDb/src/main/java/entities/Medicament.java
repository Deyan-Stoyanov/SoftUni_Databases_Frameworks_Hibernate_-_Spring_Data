package entities;

import javax.persistence.*;

@Entity
@Table(name = "medicaments")
public class Medicament {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    private String name;

    public Medicament(String name) {
        this.name = name;
    }

    public Medicament() {
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
