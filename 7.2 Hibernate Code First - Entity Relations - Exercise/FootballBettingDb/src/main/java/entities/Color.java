package entities;

import javax.persistence.*;

@Entity
@Table(name = "colors")
public class Color {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    private String name;

    public Color(String name) {
        this.name = name;
    }

    public Color() {
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
