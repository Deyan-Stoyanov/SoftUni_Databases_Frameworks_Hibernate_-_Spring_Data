package product;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "production_batches")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class ProductionBatch {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "batch", targetEntity = BasicShampoo.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<BasicShampoo> shampoos;

    public ProductionBatch(int id, Set<BasicShampoo> shampoos) {
        this.id = id;
        this.shampoos = shampoos;
    }

    public ProductionBatch() {
    }

    public int getId() {
        return id;
    }

    public Set<BasicShampoo> getShampoos() {
        return shampoos;
    }

    public void setShampoos(Set<BasicShampoo> shampoos) {
        this.shampoos = shampoos;
    }
}
