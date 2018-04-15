package product.shop.model.entity;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "first_name")
    @Expose
    private String firstName;
    @Column(name = "last_name")
    @Expose
    private String lastName;
    @Column(name = "age")
    @Expose
    private Integer age;
    @OneToMany
    private Set<User> friends;
    @OneToMany(mappedBy = "buyer")
    private Set<Product> boughtProducts;
    @OneToMany(mappedBy = "seller")
    @Expose
    private Set<Product> productsForSale;

    public User(String firstName, String lastName, Integer age, Set<User> friends, Set<Product> products, Set<Product> productsForSale) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.friends = friends;
        this.boughtProducts = products;
        this.productsForSale = productsForSale;
    }

    public User() {
        this.friends = new HashSet<>();
        this.boughtProducts = new HashSet<>();
        this.productsForSale = new HashSet<>();
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    public Set<Product> getBoughtProducts() {
        return boughtProducts;
    }

    public void setBoughtProducts(Set<Product> boughtProducts) {
        this.boughtProducts = boughtProducts;
    }

    public Set<Product> getProductsForSale() {
        return productsForSale;
    }

    public void setProductsForSale(Set<Product> productsForSale) {
        this.productsForSale = productsForSale;
    }
}
