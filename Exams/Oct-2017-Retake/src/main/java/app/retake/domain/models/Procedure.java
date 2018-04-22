package app.retake.domain.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "procedures")
public class Procedure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "animal_aids_procedures",joinColumns = @JoinColumn(name = "procedure_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "animal_aid_id", referencedColumnName = "id"))
    private List<AnimalAid> services;

    @ManyToOne
    private Animal animal;

    @Transient
    private BigDecimal price;

    @ManyToOne
    private Vet vet;

    @Column(name = "date_performed")
    private Date date;

    public Procedure(List<AnimalAid> services, Animal animal, BigDecimal price, Vet vet, Date date) {
        this.services = services;
        this.animal = animal;
        this.price = price;
        this.vet = vet;
        this.date = date;
    }

    public Procedure() {
        this.services = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<AnimalAid> getServices() {
        return services;
    }

    public void setServices(List<AnimalAid> services) {
        this.services = services;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
    }

    public Vet getVet() {
        return vet;
    }

    public void setVet(Vet vet) {
        this.vet = vet;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
