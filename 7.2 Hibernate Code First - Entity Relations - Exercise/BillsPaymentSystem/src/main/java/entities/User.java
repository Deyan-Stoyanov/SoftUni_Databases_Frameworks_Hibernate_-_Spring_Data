package entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name ="users")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    private String firstName;

    @Basic
    private String lastName;

    @Basic
    private String email;

    @Basic
    private String password;

    @OneToMany(mappedBy = "owner")
    private Set<BillingDetail> billingDetails;

    public User(String firstName, String lastName, String email, String password, Set<BillingDetail> billingDetails) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.billingDetails = billingDetails;
    }

    public User() {
    }

    public int getId() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<BillingDetail> getBillingDetails() {
        return billingDetails;
    }

    public void setBillingDetails(Set<BillingDetail> billingDetails) {
        this.billingDetails = billingDetails;
    }
}
