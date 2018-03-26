package app.models;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ballance")
    private BigDecimal ballance;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Account(BigDecimal ballance, User user) {
        this.ballance = ballance;
        this.user = user;
    }

    public Account() {
    }

    public long getId() {
        return id;
    }

    public BigDecimal getBallance() {
        return ballance;
    }

    public void setBallance(BigDecimal ballance) {
        this.ballance = ballance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
