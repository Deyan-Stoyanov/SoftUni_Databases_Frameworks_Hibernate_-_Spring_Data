package photography.workshop.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "accessories")
public class Accessory {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic(optional = false)
    private String name;

    @ManyToOne(targetEntity = Photographer.class,fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Photographer owner;

    public Accessory(String name, Photographer owner) {
        this.name = name;
        this.owner = owner;
    }

    public Accessory() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Photographer getOwner() {
        return owner;
    }

    public void setOwner(Photographer owner) {
        this.owner = owner;
    }
}
