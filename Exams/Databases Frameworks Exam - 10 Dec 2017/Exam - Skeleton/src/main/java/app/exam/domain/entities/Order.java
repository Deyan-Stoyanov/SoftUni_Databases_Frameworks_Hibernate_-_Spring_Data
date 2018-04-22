package app.exam.domain.entities;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String customer;
    @Column
    private Date date;
    @Column
    @Enumerated(value = EnumType.STRING)
    private OrderType type;
    @Transient
    private BigDecimal totalPrice;
    @NotNull
    @ManyToOne
    private Employee employee;
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    public Order(String customer, Date date, OrderType type, BigDecimal totalPrice, Employee employee, List<OrderItem> orderItems) {
        this.customer = customer;
        this.date = date;
        this.type = type;
        this.totalPrice = totalPrice;
        this.employee = employee;
        this.orderItems = orderItems;
    }

    public Order() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public OrderType getType() {
        return type;
    }

    public void setType(OrderType type) {
        this.type = type;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
