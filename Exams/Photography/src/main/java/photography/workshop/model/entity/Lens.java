package photography.workshop.model.entity;

import com.google.gson.annotations.Expose;

import javax.persistence.*;

@Entity
@Table(name = "lenses")
public class Lens {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Expose
    private String make;

    @Basic
    @Expose
    private Integer focalLength;

    @Basic
    @Expose
    private double maxAperture;

    @Basic
    @Expose
    private String compatibleWith;

    @ManyToOne(targetEntity = Photographer.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Photographer owner;

    public Lens(String make, Integer focalLength, double maxAperture, String compatibleWith, Photographer owner) {
        this.make = make;
        this.focalLength = focalLength;
        this.maxAperture = maxAperture;
        this.compatibleWith = compatibleWith;
        this.owner = owner;
    }

    public Lens() {
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

    public Integer getFocalLength() {
        return focalLength;
    }

    public void setFocalLength(Integer focalLength) {
        this.focalLength = focalLength;
    }

    public double getMaxAperture() {
        return maxAperture;
    }

    public void setMaxAperture(double maxAperture) {
        this.maxAperture = maxAperture;
    }

    public String getCompatibleWith() {
        return compatibleWith;
    }

    public void setCompatibleWith(String compatibleWith) {
        this.compatibleWith = compatibleWith;
    }

    public Photographer getOwner() {
        return owner;
    }

    public void setOwner(Photographer owner) {
        this.owner = owner;
    }
}
