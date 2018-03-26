package entities;

import javax.persistence.*;

@Entity
@Table(name = "diagnoses")
public class Diagnose {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    private String name;

    @Basic
    private String comments;

    public Diagnose(String name, String comments) {
        this.name = name;
        this.comments = comments;
    }

    public Diagnose() {
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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
