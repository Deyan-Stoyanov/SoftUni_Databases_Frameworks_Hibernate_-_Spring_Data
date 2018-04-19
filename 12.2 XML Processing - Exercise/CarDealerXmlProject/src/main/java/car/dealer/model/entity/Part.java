package car.dealer.model.entity;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "parts")
public class Part {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Expose
    @Basic
    private String name;

    @Basic
    @Expose
    private BigDecimal price;

    @Basic
    @Expose
    private int quantity;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL, targetEntity = Supplier.class)
    @Expose
    private Supplier supplier;

    @Expose
    @ManyToMany(mappedBy = "parts")
    private List<Car> cars;

    public Part(String name, BigDecimal price, int quantity, Supplier supplier, List<Car> cars) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.supplier = supplier;
        this.cars = cars;
    }

    public Part() {
        this.cars = new ArrayList<>();
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
