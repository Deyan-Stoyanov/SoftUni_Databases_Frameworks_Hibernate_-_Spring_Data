package photography.workshop.model.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "cameras")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public abstract class BasicCamera {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic(optional = false)
    private String make;

    @Basic(optional = false)
    private String model;

    @Basic
    private boolean isFullFrame;

    @Column(name = "min_iso", nullable = false)
    @Size(min = 100)
    private Integer minIso;

    @Basic
    private Integer maxIso;

    public BasicCamera(String make, String model, boolean isFullFrame, Integer minIso, Integer maxIso) {
        this.make = make;
        this.model = model;
        this.isFullFrame = isFullFrame;
        this.minIso = minIso;
        this.maxIso = maxIso;
    }

    public BasicCamera() {
    }

    public Long getId() {
        return id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isFullFrame() {
        return isFullFrame;
    }

    public void setFullFrame(boolean fullFrame) {
        isFullFrame = fullFrame;
    }

    public Integer getMinIso() {
        return minIso;
    }

    public void setMinIso(Integer minIso) {
        this.minIso = minIso;
    }

    public Integer getMaxIso() {
        return maxIso;
    }

    public void setMaxIso(Integer maxIso) {
        this.maxIso = maxIso;
    }
}
