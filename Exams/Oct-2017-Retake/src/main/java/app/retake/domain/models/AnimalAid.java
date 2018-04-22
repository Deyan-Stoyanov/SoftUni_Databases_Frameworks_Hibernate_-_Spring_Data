package app.retake.domain.models;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "animal_aids")
public class AnimalAid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @Size(min = 3)
    private String name;

    @Column
    private BigDecimal price;

    @ManyToMany(mappedBy = "services", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Procedure> procedures;

    public AnimalAid(String name, BigDecimal price, List<Procedure> procedure) {
        this.name = name;
        this.price = price;
        this.procedures = procedure;
    }

    public AnimalAid() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<Procedure> getProcedures() {
        return procedures;
    }

    public void setProcedures(List<Procedure> procedures) {
        this.procedures = procedures;
    }
}
