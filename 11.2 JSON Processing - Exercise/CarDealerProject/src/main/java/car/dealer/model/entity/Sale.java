package car.dealer.model.entity;

import com.google.gson.annotations.Expose;

import javax.persistence.*;

@Entity
@Table(name = "sales")
public class Sale {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Expose
    private double discount;

    @OneToOne
    @Expose
    private Car car;

    @OneToOne
    @Expose
    private Customer customer;

    public Sale(double discount, Car car, Customer customer) {
        this.discount = discount;
        this.car = car;
        this.customer = customer;
    }

    public Sale() {
    }

    public Long getId() {
        return id;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
