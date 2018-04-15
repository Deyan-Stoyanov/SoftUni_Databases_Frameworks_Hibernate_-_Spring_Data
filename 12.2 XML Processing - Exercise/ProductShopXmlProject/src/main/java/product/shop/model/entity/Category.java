package product.shop.model.entity;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Category {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    @Expose
    @XmlElement(name = "name")
    private String name;
    @ManyToMany(mappedBy = "categories")
    private Set<Product> products;

    public Category(String name, Set<Product> products) {
        this.name = name;
        this.products = products;
    }

    public Category() {
        this.products = new HashSet<>();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
