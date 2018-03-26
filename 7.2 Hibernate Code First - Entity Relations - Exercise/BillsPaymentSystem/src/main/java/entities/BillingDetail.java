package entities;

import javax.persistence.*;

@Entity
@Table(name ="billing_details")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class BillingDetail {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "number")
    private int number;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private User owner;

    public BillingDetail(int number, User owner) {
        this.number = number;
        this.owner = owner;
    }

    public BillingDetail() {
    }

    public int getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
