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
    private String make;

    @Basic
    private String model;

    @Basic
    private String travelledDistance;

    @ManyToMany
    @JoinTable(name = "parts_cars", joinColumns = @JoinColumn(name = "parts_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "cars_id", referencedColumnName = "id"))
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
