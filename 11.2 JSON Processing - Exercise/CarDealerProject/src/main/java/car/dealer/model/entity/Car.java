package car.dealer.model.entity;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Expose
    private String make;

    @Basic
    @Expose
    private String model;

    @Basic
    @Expose
    private String travelledDistance;

    @Expose
    @ManyToMany(mappedBy = "cars")
    private List<Part> parts;

    public Car(String make, String model, String travelledDistance, List<Part> parts) {
        this.make = make;
        this.model = model;
        this.travelledDistance = travelledDistance;
        this.parts = parts;
    }

    public Car() {
        this.parts = new ArrayList<>();
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

    public String getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(String travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }
}
