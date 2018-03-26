package entities;

import javax.persistence.*;

@Entity
@Table(name = "competition_types")
public class CompetitionType {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    private String name;

    public CompetitionType(String name) {
        this.name = name;
    }

    public CompetitionType() {
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
